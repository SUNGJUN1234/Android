package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
    lateinit var email : EditText
    lateinit var pw : EditText
    lateinit var login : Button
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 1. View 들의 id 값을 찾아오자 (findViewById)
        email = findViewById<EditText>(R.id.etEmail)
        pw = findViewById<EditText>(R.id.etPw)
        login = findViewById<Button>(R.id.btnLogin)
        // 2. Button에 Event 달아주기 (setOnClickListener)
        login.setOnClickListener() {
            // 2-1. EditText에 적혀있는 email, password값을 가져오기
            // ( email, pw : 변수 ) ---> 문자열로 형변환
            var nowEmail = email.text.toString()
            var nowPw = pw.text.toString()
            // 2-2. 가져온 email, pw가 'smhrd@smhrd.or.kr','qwer1234' 가
            // 일치하는지 판단 (조건식)
            if (nowEmail == "smhrd@smhrd.or.kr" && nowPw == "qwer1234") {
                // 맞다면 Toast로 "로그인 성공"
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                // 다르다면 Toast로 "로그인 실패"
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}