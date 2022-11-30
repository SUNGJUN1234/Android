package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val btnSub = findViewById<Button>(R.id.btnSub)

        btnSub.setOnClickListener {

            val title = etTitle.text.toString()
            val url = etUrl.text.toString()

            val intent = Intent()

            intent.putExtra("title",title)
            intent.putExtra("url",url)

            setResult(RESULT_OK,intent)

            finish()
        }



    }
}