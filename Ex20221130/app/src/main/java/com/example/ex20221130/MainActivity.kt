package com.example.ex20221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var phoneList = ArrayList<PhoneVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById<ListView>(R.id.lv)

        // 1. 화면에서 ListView의 위치 정해주기 (xml파일에서)               v
        // 2. ListView 한 칸에 들어갈 디자인 정해주기 (xml파일에서)          v
        // 3. ListView에 들어갈 데이터 만들기
        // 이미지 뷰에 들어갈 Image의 ID값 (Int)
        // 이름, 전화번호 (String)
        // 다양한 자료형을 사용하기 위해 하나의 클래스를 만들어주자 (VO)
        val p1 = PhoneVO(R.drawable.t1, "조자연", "010-1234-5678")
        val p2 = PhoneVO(R.drawable.t2, "선영표", "010-8765-4321")
        val p3 = PhoneVO(R.drawable.t3, "채수민", "010-1111-1111")
        val p4 = PhoneVO(R.drawable.t4, "강예진", "010-2222-2222")
        val p5 = PhoneVO(R.drawable.t5, "나예호", "010-3333-3333")

        phoneList.add(p1)
        phoneList.add(PhoneVO(R.drawable.t2, "선영표", "010-8765-4321"))
        phoneList.add(p3)
        phoneList.add(PhoneVO(R.drawable.t4, "강예진", "010-2222-2222"))
        phoneList.add(p5)

        // ********* 4. Adapter만들기 *********
        // 데이터의 자료형이 내가만든 자료형(VO)이기 때문에
        // 안드로이드에서 기본적으로 제공하는 ArrayAdapter는 사용이 불가능
        // Adapter : 데이터와 템플릿을 합쳐서 ListView에 적재시켜주는 역할
        // 생성자로 PhoneAdapter 클래스에 정보를 보내주자
        val adapter = PhoneAdapter(applicationContext, R.layout.phone_list, phoneList)

        // 5. ListView에 Adapter 적용
        lv.adapter = adapter

        // 6. Event달아주기
    }
}