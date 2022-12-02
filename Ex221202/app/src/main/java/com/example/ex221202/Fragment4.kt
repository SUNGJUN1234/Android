package com.example.ex221202

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

// Fragment는 androidx에 있는걸로 import 해주자
class Fragment4 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // id값 찾아오기
        var view = inflater.inflate(R.layout.fragment_4, container, false)

        val etUrl = view.findViewById<EditText>(R.id.etUrl)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        // 버튼을 클릭했을 때 일어나야하는 일
        btnSend.setOnClickListener {
        // SharedPreference에 Url값 저장하기
            var url = etUrl.text.toString()
        // 1. SharedPreference 가져오기
        // 1) 이름 지정
        // 2) 모드 설정
        val spf = requireActivity().getSharedPreferences(
            "mySpf",
            Context.MODE_PRIVATE
        )
        // 2. SharedPreference 에 데이터를 저장할 수 있는 editor 가져오기
        var editor = spf.edit()
        // 3. editor를 통해서 데이터 저장하기 ( 마지막엔 commit 해주자 )
        editor.putString("url",url)
        // + Fragment1 에서 꺼내서 url에 설정
            editor.commit()

        }

        return view
    }

}