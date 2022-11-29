package com.example.ex20221129

import android.content.ClipData.Item
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class BoardActivity : AppCompatActivity() {

    lateinit var tvMain: TextView
    lateinit var btnRegister: Button
    lateinit var btnBoard:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        tvMain = findViewById<TextView>(R.id.tvMain)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val lvPost = findViewById<ListView>(R.id.lvPost)
        val etBoard = findViewById<EditText>(R.id.etBoard)
        btnBoard = findViewById<Button>(R.id.btnBoard)

        btnLogin.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)

            launcher.launch(intent)
        }

        lvPost.setOnItemClickListener { p0, p1, p2, p3 ->
            // p0 = parent , p1 = view , p2 = position , p3 = id
            // adapterView : ListView에 대한 정보
            // view : ListView가 있는 현재 페이지에 대한 정보
            // position : 내가 클릭한 item위치
            // id : 내가 클릭한 item의 고유값
            if (p0 != null) {
                Toast.makeText(this, p0.getItemAtPosition(p2).toString(), Toast.LENGTH_SHORT).show()
            }
        }


        // 1. Container 결정
        // -> ListView의 위치를 결정!! (xml에서 View와 id를 지정해두기)

        // 2. Template 결정
        // -> 각 항목(Item)에 적용될 디자인을 결정!
        // -> Layout패키지에 xml형태로 생성!!
        // board_list.xml 최상위 레이아웃 : TextView
        // 이 때, id는 tvBoard

        // 3. Item 결정
        // 여러개의 자료형을 표현하고 싶다면 VO (Value Object)
        val data = ArrayList<String>()
        data.add("1. 어제점심 대접")
        data.add("2. 오늘점심 버거킹")
        data.add("3. 내일점심 좋은국밥")

        // 4. Adapter 결정
        // Adapter : 디자인(항목 뷰, 템플릿) + Item 결합해서
        // AdapterView에 동적으로 뿌려주는 역할!!

        // ArrayAdapter를 사용하자
        // ArrayAdapter를 사용할 수 있는 조건 :
        // 템플릿이 TextView , 아이템 요소가 스트링
        // ArrayAdapter안에 들어가는 생성자 가지
        // 1) 페이지 정보
        // 2) 템플릿(항목 뷰)
        // 3) 적용할 TextView의 아이디
        // 4) Item
        val adapter = ArrayAdapter<String>(this, R.layout.board_list, R.id.tvBorad, data)

        // 5. Container에 Adapter 부착
        lvPost.adapter = adapter

        // 6. Event 처리
        // 1) btnBoard를 클릭했을 때,
        // 2) etBoard의 값을 가져와서
        // 3) val input에 저장
        // 4) data에 input을 추가
        btnBoard.setOnClickListener {
            val input:String = etBoard.text.toString()
            data.add(input)
            // adapter를 새로고침하자!
            adapter.notifyDataSetChanged()
            // 창 비워주기
            etBoard.text = null
        }


        // 게시글을 클릭했을 때 그 게시글을 삭제하는 이벤트
        lvPost.setOnItemClickListener { adapterView, view, i, l ->

            // AlertDialog 옵션 정보를 담는 builder 생성
            val builder = AlertDialog.Builder(this)
            builder.setTitle("게시글 삭제")
            .setMessage("정말 삭제하시겠습니까?")
            .setPositiveButton("삭제", DialogInterface.OnClickListener{ p0,p1 ->
                data.removeAt(i)
                adapter.notifyDataSetChanged()
            })
            .setNegativeButton("취소", null)
            .show()


            // 주의
            // builder를 통해 옵션을 단 이후
            // 맨 마지막에는 무조건!! show()함수를 달아야 한다


        }













    }// onCreate() 경계

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            btnRegister.isEnabled = true
            btnBoard.isEnabled = true
            tvMain.text = "로그인 성공!!"
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
        } else if (it.resultCode == RESULT_CANCELED) {
            btnRegister.isEnabled = false
            btnBoard.isEnabled = false
            tvMain.text = "로그인 실패!!"
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
        }
    }
}