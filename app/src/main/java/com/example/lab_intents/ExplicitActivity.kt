package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ExplicitActivity : AppCompatActivity() {
    private lateinit var textInput: EditText
    private lateinit var btnVoRed: Button
    private lateinit var btnOtkazi: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        textInput = findViewById(R.id.textInput)
        btnVoRed = findViewById(R.id.btnVoRed)
        btnOtkazi = findViewById(R.id.btnOtkazi)



        btnVoRed.setOnClickListener{
            Intent().let { intent ->
                intent.putExtra("textData",textInput.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }

        btnOtkazi.setOnClickListener{
            Intent().let { intent ->
                setResult(Activity.RESULT_CANCELED,intent)
                finish()
            }

        }

    }

}