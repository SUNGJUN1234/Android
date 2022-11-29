package com.example.ex20221128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import kotlin.random.Random

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)


        // 1. Container 결정(위치 결정)
        val lvLunch = findViewById<ListView>(R.id.lvLunch)
        val btnLunch = findViewById<Button>(R.id.btnLunch)
        val tvLunch = findViewById<TextView>(R.id.tvLunch)
        val etAdd = findViewById<EditText>(R.id.etAdd)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        // 나중에는 ViewBinding 기법으로 초기화 하자

        // 2.Template 결정 (아이템 디자인)
        // board_list.xml

        // 3.Item결정
        val data = ArrayList<String>()
        data.add("돈까스")
        data.add("제육볶음")

        val adapter = ArrayAdapter<String>(
            this,
            R.layout.lunch_list,
            R.id.tvMenu,
            data
        )


        // 5. Container 에 Adapter 부착
        lvLunch.adapter = adapter
        
        // 6. Event 처리
        // 1) btnAdd를 눌렀을 때, etAdd의 값을 가져와서
        // 2) data에 추가!!
        // 3) adapter 새로고침

        btnAdd.setOnClickListener {
            data.add(etAdd.text.toString())
            etAdd.text = null
            adapter.notifyDataSetChanged()
        }

        btnLunch.setOnClickListener {

            val rd = Random
            val menu = rd.nextInt(data.size)
            tvLunch.text = data.get(menu)

        }




    }
}