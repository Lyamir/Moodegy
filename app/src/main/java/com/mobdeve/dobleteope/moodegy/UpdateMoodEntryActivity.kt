package com.mobdeve.dobleteope.moodegy

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.*
import com.mobdeve.dobleteope.moodegy.data.daos.*
import kotlinx.android.synthetic.main.activity_updatemoodentry.*
import java.io.File

private const val REQUEST_CODE = 11
private const val FILE_NAME = "photo.jpg"
private lateinit var photoFile: File
class UpdateMoodEntryActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var moodEntryDao: MoodEntryDao
    lateinit var moodDao: MoodDao
    lateinit var activityDao: ActivityDao
    lateinit var activityEntryDao: ActivityEntryDao
    lateinit var photoDao: PhotoDao
    lateinit var descriptionDao: DescriptionDao

    lateinit var moodList: List<Mood>
    lateinit var activityList: List<Activity>
    lateinit var activityEntry: ActivityEntry

    lateinit var newMoodList: ArrayList<String>
    lateinit var  newActivityList: ArrayList<String>
    lateinit var moodEntry: MoodEntry
    private var withPhoto: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatemoodentry)
        withPhoto = false

        moodEntry = Gson().fromJson(intent.getStringExtra("moodEntry"), MoodEntry::class.java)



        db = AppDatabase.getDatabase(this)
        moodEntryDao = db.moodEntryDao()
        moodDao = db.moodDao()
        activityDao = db.activityDao()
        activityEntryDao = db.activityEntryDao()
        photoDao = db.photoDao()
        descriptionDao = db.descDao()

        activityEntry = activityEntryDao.getEntryFromMoodID(moodEntry.id)

        val description = descriptionDao.getActivity(moodEntry.id)
        if (description != null)
            updatedescription_edittext.setText(description.text)

        val photo = photoDao.getActivity(moodEntry.id)
        if (photo != null){
            updatepicture_view.setImageBitmap(photo.photo)
            withPhoto = true
        }


        updatepicture_btn.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

            val fileProvider = FileProvider.getUriForFile(this, "com.mobdeve.dobleteope.fileprovider", photoFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            try{
                startActivityForResult(intent, REQUEST_CODE)
            }catch (ex: ActivityNotFoundException){
                Toast.makeText(this, "Unable to Open Camera", Toast.LENGTH_SHORT).show()
            }
        }

        editactivities_textview.setOnClickListener{
            val intent = Intent(this, ViewActivities::class.java)
            startActivity(intent)
        }

        editmoods_textview.setOnClickListener{
            val intent = Intent(this, ViewMoods::class.java)
            startActivity(intent)
        }

        removepicture_btn.setOnClickListener{
            if(withPhoto){
                updatepicture_view.setImageBitmap(null)
                withPhoto = false
            }
        }

        updatemoodentry_btn.setOnClickListener{
            val id = moodEntry.id
            var activityID: Int = 0
            for(activity in activityList){
                if(activity.name == updateactivity_autocompletetextview.text.toString()){
                    activityID = activity.id
                    break
                }
            }
            activityEntryDao.update(ActivityEntry(id, activityID, updateactivity_autocompletetextview.text.toString()))

            moodEntryDao.update(MoodEntry(id, moodEntry.dateTime, updatemood_autocompletetextview.text.toString()))


            updatepicture_view.invalidate()
            val drawable: BitmapDrawable = updatepicture_view.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            if(withPhoto){
                if(photo == null)
                    photoDao.insert(Photo(moodEntry.id, bitmap))
                else
                    photoDao.update(Photo(moodEntry.id, bitmap))
            }

            if(updatedescription_edittext.text.toString().isNotEmpty()){
                if(description == null)
                    descriptionDao.insert(Description(moodEntry.id, updatedescription_edittext.text.toString()))
                else
                    descriptionDao.update(Description(moodEntry.id, updatedescription_edittext.text.toString()))
            }
            else{
                if(description != null)
                    descriptionDao.delete(Description(moodEntry.id, description.text))
            }

            finish()
        }

        deletemoodentry_btn.setOnClickListener {
            moodEntryDao.delete(moodEntry)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun getPhotoFile(fileName: String): File{
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == android.app.Activity.RESULT_OK){
            val image = BitmapFactory.decodeFile(photoFile.absolutePath)
            withPhoto = true
            updatepicture_view.setImageBitmap(image)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onResume() {
        super.onResume()

        moodList = moodDao.getAll()
        activityList = activityDao.getAll()

        newMoodList = ArrayList<String>()
        newActivityList = ArrayList<String>()

        for(mood in moodList)
            newMoodList.add(mood.name)

        if(!newMoodList.contains(moodEntry.moodName))
            newMoodList.add(moodEntry.moodName)

        for(activity in activityList)
            newActivityList.add(activity.name)

        if(!newActivityList.contains(activityEntry.activityName))
            newActivityList.add(activityEntry.activityName)

        val moodListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newMoodList)
        moodListAdapter.notifyDataSetChanged()
        updatemood_autocompletetextview.setText(moodEntry.moodName)
        updatemood_autocompletetextview.setAdapter(moodListAdapter)


        val activityListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newActivityList)
        activityListAdapter.notifyDataSetChanged()
        updateactivity_autocompletetextview.setText(activityEntry.activityName)
        updateactivity_autocompletetextview.setAdapter(activityListAdapter)

    }


}
