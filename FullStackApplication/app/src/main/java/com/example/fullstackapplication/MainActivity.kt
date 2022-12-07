package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.fullstackapplication.auth.IntroActivity
import com.example.fullstackapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // auth에 담겨있는 기능
    // createUsersWithEmailAndPassword : 회원가입 (email, pw)
    // SignInWithEmailAndPassword : 로그인 (email, pw)
    // SignInAnonymous : 익명로그인 ()
    // SignOut : 로그아웃 (페이지 이동기능 x , UID 값을 null 로 만들어준다 )




    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnvMain = findViewById<BottomNavigationView>(R.id.bnvMain)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)

        auth = Firebase.auth

        // 로그아웃 기능
        imgLogout.setOnClickListener{

            auth.signOut()

            // 로그아웃하고나면 IntroActivity로 이동
            val intent = Intent(this,IntroActivity::class.java)
            // 이동하기 전에 이전에 쌓여있는 Activity를 모두 날려주기
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            Fragment1()
        ).commit()

        bnvMain.setOnItemSelectedListener {
            // it : 내가 클릭한 item 정보

            // Fragment는 Activity 위에 존재하기에 독립적인 동작이 불가능하다
            when(it.itemId){
                R.id.tab1 -> {
                    // beginTransaction() : Fragment 간의 삭제 / 수정할 때의 도움을 주는 메서드
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
                R.id.tab5 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment5()
                    ).commit()
                }
            }

            // 이벤트가 끝났다는걸 알려주는 장치
            true
        }


    }
}