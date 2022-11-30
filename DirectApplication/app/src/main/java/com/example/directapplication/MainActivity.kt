package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var urlList = ArrayList<DirectVO>()
    var title:String? = ""
    var url:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val lv = findViewById<ListView>(R.id.lv)

        // 1. btnAdd (추가)를 누르면  AddActivity(Sub)로 이동
        // 단, 받아올 데이터 값이 있음 : 양방향 인텐트

        // 2. AddActivity에서 btnSend(Button)를 누르면,
        // EditText에 적혀있는title, url값을 가지고 MainActivity로 이동
        // (이동할 때 finish() 해주기)

        // 3. AddActivity에서 보낸 값(intent안에 들어있음)을 받아주자
        // 런쳐 사용하기

        val d1 = DirectVO("구글","https://www.google.com/")
        val d2 = DirectVO("네이버","https://www.naver.com/")

        urlList.add(d1)
        urlList.add(d2)

        // 4. ListView 만들기
        // 4-1. ListView 위치 정해주기
        // 4-2. ListView 한 칸에 들어갈 디자인 (템플릿 - xml) 만들기
        // 4-3. AddActivity에서 받아온 결과값으로 ListView에 들어갈 데이터 만들기
        //      (title,url ---> 하나의 자료형으로 묶어주기 (DirectVO))
        // 4-4. Adapter 만들기

        val adapter = UrlAdapter(applicationContext, R.layout.url_list, urlList)
        // 4-5. Adapter ListView에 적용!!
        lv.adapter = adapter

        btnAdd.setOnClickListener {
            var intent = Intent(this,AddActivity::class.java)

            launcher.launch(intent)
            adapter.notifyDataSetChanged()
        }

    }


    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== RESULT_OK){
            title= it.data?.getStringExtra("title")
            url = it.data?.getStringExtra("url")
            urlList.add(DirectVO(title.toString(),url.toString()))
        }
    }
}