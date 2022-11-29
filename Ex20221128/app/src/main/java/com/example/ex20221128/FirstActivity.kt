package com.example.ex20221128

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView

class FirstActivity : AppCompatActivity() {

    // 배경 색상을 저장해 second로 보내자!!
    var color:String = "white"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

//        val btnNext = findViewById<Button>(R.id.btnNext)
//
//        // btnNext 를 클릭했을 때, SecondActivity로 이동!
//        btnNext.setOnClickListener {
//            // Activity로 이동하는 Intent (명시적 인텐트 )
//            // 시작 Activity, 도착 Activity
//            // this        ,  java class
//            // kclass로 만들어진 Activity를 java class로 바꿔줘야 한다
//            // 액티비티명::class.java ---> Kclass가 java class로 바뀌게 된다
//            var intent = Intent(this,
//            SecondActivity::class.java)
//            // 실행!!!
//            startActivity(intent)
//        }

        val btnNext = findViewById<Button>(R.id.btnNext)

        val lv = findViewById<ListView>(R.id.lv)

        // 버튼 클릭을 감지하는 리스너를 장착
        // setOnClickListener
        // 해당 버튼의 자식을 클릭할 때 감지하는 Listener
        // setOnItemClickListener
        lv.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                // p2 = position
                // p3 = id

                if(p0 != null){
                color = p0.adapter.getItem(p2).toString()
                }
//                if(p2 == 0){
//                    color = "Purple"
//                }else if(p2 == 1){
//                    color = "Yellow"
//                }else if(p2 == 2){
//                    color = "Pink"
//                }

                Log.d("찍어보자",color)


            }
        })



        // inner class
        // 익명 클래스 anonymous class
        //        btnNext.setOnClickListener (object : View.OnClickListener{
        //            override fun onClick(v : View?){
        //                TODO("Not Yet implemented")
        //            }
        //        })
        //
        //        ==
        //
        //        btnNext.setOnClickListener { v->
        //
        //        }


        // 안드로이드 4대 구성요소
        // Activity : 화면을 구성
        // Service : Activity에서 화면만 뺀것
        // Broadcast Receiver : 외부의 상황 감지
        // Content Provider : 정보 제공자(다른 어플에서 정보를 받는것)
        // (ex 카톡에서 다른사람에게 연락처를 전송하고자 할 때 연락처 어플에서 연락처 정보를 카톡으로 넘김)

        // 위의 4대 구성요소간 정보를 매개하는 객체가 바로 Intent

        // Intent의 사용방법 2가지
        // 명시적 explicit
        // 묵시적 implicit

        // btnNext를 누르면 SecondActivity로 color코드를 가지고 넘어간다
        btnNext.setOnClickListener {
            var intent = Intent(
                this@FirstActivity,
                SecondActivity::class.java
            )
            intent.putExtra("COLOR",color)

            // 단방향 호출
            startActivity(intent)
        }
    }

    // 오버라이딩 단축키 Ctrl + o (overriding)
    // 혹은 우클릭 -> generate -> override methods
    override fun onStart() {
        super.onStart()
        Log.d("생명주기", "onStart입니다")
    }

    override fun onStop() {
        super.onStop()
        Log.d("생명주기", "onStop입니다")
    }

    override fun onResume() {
        super.onResume()
        Log.d("생명주기", "onResume입니다")
    }

    override fun onPause() {
        super.onPause()
        Log.d("생명주기", "onPause입니다")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("생명주기", "onRestart입니다")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("생명주기", "onDestroy입니다")
    }
}