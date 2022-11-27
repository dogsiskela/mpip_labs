package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private lateinit var btnGoToExplicitActivity: Button
    private lateinit var btnGoToImplicitActivity: Button
    private lateinit var btnShareContent: Button
    private lateinit var btnUploadImage: Button
    private lateinit var resultExplicitActivity: TextView

    private var EXPLICIT_ACTIVITY_REQUEST_CODE = 1
    private var PICK_IMAGE = 2

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == EXPLICIT_ACTIVITY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null && data.getStringExtra("textData") != null){
                    resultExplicitActivity.setText(data.getStringExtra("textData"))
                }
            }
        }
        if(requestCode == PICK_IMAGE){
            System.out.println(data)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToExplicitActivity = findViewById(R.id.btnGoToExplicitActivity)
        btnGoToImplicitActivity = findViewById(R.id.btnGoToImplicitActivity)
        btnShareContent= findViewById(R.id.btnShareContent)
        btnUploadImage = findViewById(R.id.btnUploadImage)
        resultExplicitActivity = findViewById(R.id.resultExplicitActivity)


        btnGoToExplicitActivity.setOnClickListener{
            Intent(this,ExplicitActivity::class.java).let {i->
                startActivityForResult(i,EXPLICIT_ACTIVITY_REQUEST_CODE)
            }
        }

        btnGoToImplicitActivity.setOnClickListener{
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
            }.let { intent ->
                val mainIntent = Intent(Intent.ACTION_MAIN, null)
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

                val activitiesList = packageManager.queryIntentActivities(mainIntent, PackageManager.GET_ACTIVITIES).map { el->el.activityInfo.name }
                intent.putExtra("activitiesList",activitiesList as Serializable)

                startActivity(intent)
            }
        }

        btnShareContent.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, "MPiP Send Title")
                putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        btnUploadImage.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }
}