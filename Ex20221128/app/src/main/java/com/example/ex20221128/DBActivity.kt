package com.example.ex20221128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text

class DBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        val loginId = intent.getStringExtra("loginId")
        val loginPw = intent.getStringExtra("loginPw")

        Log.d("아이디/패스워드",loginId+" / "+loginPw)

        // ID : csj
        // PW : 1234

        if(loginId=="csj" && loginPw=="1234"){
                tvResult.setText("로그인 성공")
        }else{
                tvResult.text = "로그인 실패"
        }

    }
}