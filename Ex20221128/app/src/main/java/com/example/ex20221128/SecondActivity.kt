package com.example.ex20221128

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnPre = findViewById<Button>(R.id.btnPre)
        val cl = findViewById<ConstraintLayout>(R.id.cl)
        // 제네릭 : 타입을 강하게 체크하는 기능

        // 1. intent안에 COLOR 키값을 통해 색깔을 꺼내자
        val color:String = intent.getStringExtra("COLOR")!!

        Log.d("색깔잘넘어왔니?",color)

        // Java
        // 문자열 -> 정수로 바꿀 때
        // String.parseInt()

        // 문자열 -> Color로 바꿀 때
        // color.parseColor()

        // 2. 배경색상을  COLOR로 변경하자
        cl.setBackgroundColor(Color.parseColor(color))



        // btnPre를 눌렀을 때 이전 페이지 (FirstActivity로 돌아간다)
        btnPre.setOnClickListener {
//            var intent = Intent(this,
//            FirstActivity::class.java)
//            startActivity(intent)   // stack에 Activity를 넣는다
            finish()    // stack구조에서 out시키기
        }



    }
}