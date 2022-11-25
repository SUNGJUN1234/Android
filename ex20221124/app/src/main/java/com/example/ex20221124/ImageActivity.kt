package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

lateinit var img : ImageView
lateinit var pre : Button
lateinit var next : Button

class ImageActivity : AppCompatActivity() {

    // 뷰를 관리하는 배열 선언하기
    val imgArray = intArrayOf(R.drawable.pink,R.drawable.black
        ,R.drawable.blue,R.drawable.yellow,R.drawable.red)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        img = findViewById(R.id.img)
        pre = findViewById(R.id.btnPre)
        next = findViewById(R.id.btnNext)

        var index:Int = 0
        img.setImageResource(imgArray[index])

        // 1. Pre버튼 클릭 시 (SetOnClickListener)
        // 1-1. index -1
        // 해당 index에 있는 img의 id를 가져와서
        // ImageView에 set하자!
        // index의 조건 : index값이 0보다 작으면 size-1 로 돌아가자
        pre.setOnClickListener {
            if(index==0){
                index = imgArray.size-1
            }else{
                --index
            }
            img.setImageResource(imgArray[index])
        }

        // 2. Next버튼 클릭 시
        // 2-1. index +1
        // 해당 index에 있는 img의 id를 가져와서
        // ImageView에 set하자!
        // index의 조건 : index값이 size-1보다 크면  0으로 돌아가자
        next.setOnClickListener {
            if(index==imgArray.size-1){
                index = 0
            }else{
                ++index
            }
            img.setImageResource(imgArray[index])
        }



    }
}