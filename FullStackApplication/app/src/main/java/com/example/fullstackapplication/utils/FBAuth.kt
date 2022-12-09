package com.example.fullstackapplication.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    companion object{

        lateinit var auth : FirebaseAuth

        // 현재 유저의 Uid값을 가져오는 함수
        fun getUid() : String{

            auth = FirebaseAuth.getInstance()
            return auth.currentUser!!.uid

        }


        // 현재 시간을 가져오는 함수
        fun getTime() : String{
            // Calender 라는 객체는 getInstance() 메서드로만 생성할 수 있다
            val currentTime = Calendar.getInstance().time

            // 시간을 나타낼 형식, 어느 위치의 시간을 가져올건지 설정하는 메서드
            // SimpleDateFormat()
            val time = SimpleDateFormat(
                "yyyy.MM.dd HH:mm:ss",
                Locale.KOREA
            ).format(currentTime)
            return time


            }

    }


}