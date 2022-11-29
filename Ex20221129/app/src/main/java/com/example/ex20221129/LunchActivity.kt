package com.example.ex20221129

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        // Adapter View 만드는 6단계



        // 1. Container 결정 (위치 결정)
        // 2. Template 결정 (아이템 디자인)
        // 3. Item 결정 (만약, 자료형이 여러개라면 VO Class 생성)
        // 4. Adapter 결정 (만약, TextView, ArrayList<String> 사용한다면 ArrayAdapter 사용가능)
        // 5. Container 에 Adapter 부착
        // 6. Event 처리

    }
}