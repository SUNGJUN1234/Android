package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

// : = Kotlin에서 상속을 의미
class ConstraintActivity : AppCompatActivity() {
    // onCreate()  =  Activity가 실행될 때 최초 딱 한번(가장 먼저) 호출되는 메서드
    // : Activity 생명주기
    override fun onCreate(savedInstanceState: Bundle?) {
        // savedInstanceState: Bundle? = 가로모드에서 세로모드로 바꿨을 때 데이터를 유지시켜주는 기능
        super.onCreate(savedInstanceState)
        // ***** xml 과 kotlinClass 를 연결하는 코드 없으면 화면이 뜨지 않는다 *****
        setContentView(R.layout.activity_constraint)

        // 1. xml의 View에 id를 지정
        // 2. id값을 이용해서 view를 찾아온다 (findViewById)

        // R = 리소스 폴더를 의미한다
        // 내가 String타입으로 저장한 아이디인 tvResult는
        // R폴더의 id폴더 내에 String타입이 아닌 주소값(16진수)으로 저장된다
        val tvResult:TextView = findViewById<TextView>(R.id.tvResult)


    }
}