package com.example.lab_intents

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImplicitActivity : AppCompatActivity() {

    private lateinit var actionsLinearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)


        val intent = getIntent();
        val bundle = intent.extras;

        var actionsList: ArrayList<String> = bundle?.getSerializable("activitiesList") as ArrayList<String>

        actionsLinearLayout = findViewById(R.id.actionsLinearLayout)
        if(actionsList != null){
            for(s in actionsList)
            {
                val newTextView = TextView(this)
                newTextView.setText(s)
                actionsLinearLayout.addView(newTextView)
            }
        }
    }
}