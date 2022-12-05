package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import java.util.Queue
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var tvTimer : TextView
    lateinit var tvTimer2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById<TextView>(R.id.tvTimer)
        tvTimer2 = findViewById<TextView>(R.id.tvTimer2)

        val thread = TimerThread(tvTimer)
        thread.start()          // 쓰레드 클래스 안의 run()을 실행한다

        val thread2 = TimerThread(tvTimer2)
        thread2.start()


    }   // OnCreate 경계

    // Main Thread Queue(작업 영역)에
    // 작업을 추가해주는 Handler를 만들자!
    val handler = object:Handler(Looper.getMainLooper()){
        // handleMessage 오버라이딩
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val time = msg.arg1
            val tv = msg.obj as TextView

            // 메인 UI 를 건드는 작업
            // 직접적으로 view 정보를 수정하는게 아니고
            // 메인 작업 Queue에 '이 작업 해주세요' 라고 작업을 주기
            tv.setText(time.toString())


        }
    }


    // 시간 관련 쓰레드 클래스를 만들자
    inner class TimerThread(val tv : TextView) : Thread(){

        // run() 메소드가 존재!!
        // run() : 쓰레드를 동작시키면 실행되는 메소드!

        override fun run() {
            super.run()

            // 10 -> 0 ( 1초마다 1씩 감소 )
            // sleep 메서드 = 밀리초 만큼 코드를 지연시킴
            Thread.sleep(1000)

            for(i in 10 downTo  0 ){
                Log.d("타이머",i.toString())

                // message = handler에게 정보를 전달해주는 객체!!
                val message = Message()

                // message에는 필드가 3개 있다
                // arg1 : int , arg2 : int , obj : Any
                message.arg1 = i
                message.obj = tv


                handler.sendMessage(message)

                val rd = Random(1000).nextInt()
                val rdValue = Random(1000).nextInt(1000)

                Thread.sleep(rdValue.toLong())

            }
        }
    }
}


