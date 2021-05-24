package com.mobdeve.dobleteope.moodegy

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.mobdeve.dobleteope.moodegy.data.*
import kotlinx.android.synthetic.main.activity_addmoodentry.*
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


private const val REQUEST_CODE = 11
private const val FILE_NAME = "photo.jpg"
private lateinit var photoFile: File
class AddMoodEntryActivity : AppCompatActivity() {

    private var withPhoto: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmoodentry)

        withPhoto = false


        val db = AppDatabase.getDatabase(this)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()
        val photoDao = db.photoDao()
        val descriptionDao = db.descDao()

        val moodList = moodDao.getAll()
        val activityList = activityDao.getAll()

        val newMoodList = ArrayList<String>()
        for (mood in moodList){
            newMoodList.add(mood.name)
        }

        val newActivityList = ArrayList<String>()
        for (activity in activityList){
            newActivityList.add(activity.name)
        }

        val moodListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newMoodList)
        selectmood_autocompletetextview.setAdapter(moodListAdapter)
        val activityListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newActivityList)
        selectactivity_autocompletetextview.setAdapter(activityListAdapter)

        editactivities_textview.setOnClickListener{
            val intent = Intent(this, ViewActivities::class.java)
            startActivity(intent)
        }

        editmoods_textview.setOnClickListener{
            val intent = Intent(this, ViewMoods::class.java)
            startActivity(intent)
        }

        uploadpicture_btn.setOnClickListener{
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

        selectmood_autocompletetextview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if(selectactivity_autocompletetextview.text.toString().isNotEmpty())
                    addmoodentry_btn.isEnabled = true
            }

        selectactivity_autocompletetextview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if(selectmood_autocompletetextview.text.toString().isNotEmpty())
                    addmoodentry_btn.isEnabled = true
            }

        addmoodentry_btn.setOnClickListener{
            val answer: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                current.format(formatter)
            } else {
                val date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma", Locale.TAIWAN)
                formatter.format(date)
            }

            for(mood in moodList){
                if(mood.name == selectmood_autocompletetextview.text.toString()){
                    moodEntryDao.insert(MoodEntry(0, answer, mood.id))
                    break
                }
            }

            val moodEntry = moodEntryDao.getMoodEntryFromDate(answer)

            for(activity in activityList){
                if(activity.name == selectactivity_autocompletetextview.text.toString()){
                    activityEntryDao.insert(ActivityEntry(moodEntry.id, activity.id))
                }
            }

            uploadpicture_view.invalidate()
            val drawable: BitmapDrawable = uploadpicture_view.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            if(withPhoto){
                photoDao.insert(Photo(moodEntry.id, bitmap))
            }

            if(adddescription_edittext.text.toString().isNotEmpty()){
                descriptionDao.insert(Description(moodEntry.id, adddescription_edittext.text.toString()))
            }

            finish()
        }
    }

    private fun getPhotoFile(fileName: String): File{
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val image = BitmapFactory.decodeFile(photoFile.absolutePath)
            withPhoto = true
            uploadpicture_view.setImageBitmap(image)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}
