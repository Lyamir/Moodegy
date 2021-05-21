package com.mobdeve.dobleteope.moodegy

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.mobdeve.dobleteope.moodegy.data.ActivityEntry
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
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

        val moodList = moodDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        var newMoodList = ArrayList<String>()
        for (mood in moodList){
            newMoodList.add(mood.name)
        }

        var newActivityList = ArrayList<String>()
        for (activity in activityList){
            newActivityList.add(activity.name)
        }

        val moodListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newMoodList)
        selectmood_autocompletetextview.setAdapter(moodListAdapter)
        val activityListAdapter = ArrayAdapter(this, R.layout.dropdown_item, newActivityList)
        selectactivity_autocompletetextview.setAdapter(activityListAdapter)

        uploadpicture_btn.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

            val fileProvider = FileProvider.getUriForFile(this, "com.mobdeve.dobleteope.fileprovider", photoFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if(intent.resolveActivity(this.packageManager) == null)
                startActivityForResult(intent, REQUEST_CODE)
            else
                Toast.makeText(this, "Unable to Open Camera", Toast.LENGTH_SHORT).show()
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
            var answer: String? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                answer =  current.format(formatter)
            } else {
                var date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                answer = formatter.format(date)
            }

            for(mood in moodList){
                if(mood.name == selectmood_autocompletetextview.text.toString()){
                    for(activity in activityList){
                        if(activity.name == selectactivity_autocompletetextview.text.toString()){
                            moodEntryDao.insert(MoodEntry(0, answer, mood.id))
                            val moodEntry = moodEntryDao.getMoodEntryFromDate(answer)
                            activityEntryDao.insert(ActivityEntry(moodEntry.id, activity.id))
                            finish()
                        }
                    }

                }
            }


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

    override fun onResume() {
        super.onResume()

    }
}
