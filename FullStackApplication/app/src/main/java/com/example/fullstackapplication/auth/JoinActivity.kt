package com.example.fullstackapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // FirebaseAuth 선언
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinCheck = findViewById<EditText>(R.id.etJoinCheck)
        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)

        // auth 초기화
        auth = Firebase.auth
        // Firebase.auth : 로그인, 회원가입, 인증 시스템에 대한 모든 기능이 담겨있다!

        btnJoinJoin.setOnClickListener {

            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val check = etJoinCheck.text.toString()

//            Toast.makeText(this,"이메일 : ${email} " +
//                    "/ 비밀번호 : ${pw} " +
//                    "/ 확인 : ${check}",Toast.LENGTH_SHORT).show()


            // create가 보내고 있는 전달인자 2개 email / password 는
            // 실제로 회원가입 정보 전달 ( firebase 로 전달 )
            auth.createUserWithEmailAndPassword("asdf@naver.com", "12345678")
                .addOnCompleteListener(this) { task ->
                    // task에는 성공했는지 실패했는지에 대한 정보를 담겨있다
                    if (task.isSuccessful) {
                        // 성공했을 때 실행시킬 코드
                        Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    } else {
                        // 실패했을 때 실행시킬 코드
                        Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()
                    }
                }





        }
    }
}