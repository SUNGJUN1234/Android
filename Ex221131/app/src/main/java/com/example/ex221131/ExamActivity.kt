package com.example.ex221131

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ExamActivity : AppCompatActivity() {

    lateinit var  clExam:ConstraintLayout

    override fun onRestart() {
        super.onRestart()
        val sharedPreferences = getSharedPreferences("sp1",Context.MODE_PRIVATE)
        val color = sharedPreferences.getString("bgColor","white")
        clExam.setBackgroundColor(Color.parseColor(color))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        // 로그인 기능!
        // 자동 로그인
        // Application을 종료해도
        // 정보가 저장될 필요성이 있다
        // DataBase
        // - RDB (Relational DataBase)
        // - RDBMS
        // -> OracleDB / MySQL / MariaDB
        // SQL이라는 언어를 쓴다


        // SQLite -> 실제 데이터베이스 형태를 안드로이드에 내장
        // SharedPreference -> 환경설정 정보들을 공유하기 위해 사용
        // NoSQL -> (Key, Value)


        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnPink = findViewById<Button>(R.id.btnPink)
        val btnBlack = findViewById<Button>(R.id.btnBlack)
        val btnOther = findViewById<Button>(R.id.btnOther)
        clExam = findViewById<ConstraintLayout>(R.id.clExam)



        // tvResult : 변수!!
        // TV_RESULT : 상수!!
        val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        // MODE_PRIVATE : 생성한 application 내에서만 공유 가능
        // MODE_WORLD_READABLE : 다른 application 에서 읽을 수 있음
        // MODE_WORLD_WRITEABLE : 다른 applivation 에서 읽고 쓸 수 있음

        val bgColor:String? = sharedPreferences.getString("bgColor","white")
        clExam.setBackgroundColor(Color.parseColor(bgColor))

        btnRed.setOnClickListener {

            val editor = sharedPreferences.edit()
            val color:String = "#FF0000"
            editor.putString("bgColor",color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnPink.setOnClickListener {

            val editor = sharedPreferences.edit()
            val color:String = "#E91E63"
            editor.putString("bgColor",color)
            editor.commit()


            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnBlack.setOnClickListener {

            val editor = sharedPreferences.edit()
            val color:String = "#000000"
            editor.putString("bgColor",color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        // 어플리케이션을 종료해도 정보를 가지고 있다


        btnOther.setOnClickListener {

            // ColorActivity
            val intent = Intent(this, ColorActivity::class.java)
            startActivity(intent)

        }



    }
}