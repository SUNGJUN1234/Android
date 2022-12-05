package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.fullstackapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // OnCreate가 실행되면
        // 3초 뒤에 IntroActivity로 넘어가는 코드 작성
        // Thread, Intent 필요
        // 프로그램에는 MainThread 무조건 한개
        // 내가 만든 작업처리 = 무조건 SubThread
        // SubThread가 동작하고 있는 MainThread에 끼어드려면
        // Handler가 필요하다 (메세지, 객체, Thread 를 실행 시킬 수 있음)


        // 지연기능 만들기
        // 1. Handler를 가져와주자
        // 2. postDelayed({실행할 코드},지연시킬시간)
        Handler().postDelayed({
            // 3. Intent 생성
            val intent = Intent(this@SplashActivity,
                IntroActivity::class.java)
            startActivity(intent)
            finish()
        },3000)



    }
}