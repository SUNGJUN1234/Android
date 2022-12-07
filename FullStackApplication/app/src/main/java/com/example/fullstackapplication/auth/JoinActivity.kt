package com.example.fullstackapplication.auth

import android.content.Intent
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

            var isJoin = true       // 조건을 만족했는지 안했는지 확인하는 용도

            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val check = etJoinCheck.text.toString()

//            Toast.makeText(this,"이메일 : ${email} " +
//                    "/ 비밀번호 : ${pw} " +
//                    "/ 확인 : ${check}",Toast.LENGTH_SHORT).show()


            // Firebase에 규칙은 정해져 있지만 사용자는 모를 수 있으니 Toast를 통해서 규칙을 알려주자
            // EditText에 내용이 있는가?
            if(email.isEmpty()){
                isJoin=false
                Toast.makeText(this,"이메일을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            if(pw.isEmpty()){
                isJoin=false
                Toast.makeText(this,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            if(check.isEmpty()){
                isJoin=false
                Toast.makeText(this,"비밀번호 재입력을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            // 비밀번호와 재입력 비밀번호가 같은가?
            if(pw != check){
                isJoin=false
                Toast.makeText(this,"비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
            // 비밀번호가 8자리 이상인가?
            if(pw.length<8){
                isJoin=false
                Toast.makeText(this,"비밀번호는 8자리를 넘겨주세요",Toast.LENGTH_SHORT).show()
            }



            if(isJoin){
                // 회원가입을 진행

                // create가 보내고 있는 전달인자 2개 email / password 는
                // 실제로 회원가입 정보 전달 ( firebase 로 전달 )
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this) { task ->
                        // task에는 성공했는지 실패했는지에 대한 정보를 담겨있다
                        if (task.isSuccessful) {
                            // 성공했을 때 실행시킬 코드
                            Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // 실패했을 때 실행시킬 코드
                            Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()
                        }
                    }

            }








        }
    }
}