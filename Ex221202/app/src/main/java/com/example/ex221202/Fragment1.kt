package com.example.ex221202

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class Fragment1 : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)

        // WebView 찾아오기
        val wv = view.findViewById<WebView>(R.id.wv)
        // WebView에 원하는 웹페이지 띄우기


        // sharedPreference 를 사용할 수 있게 준비하자
        val spf = requireActivity().getSharedPreferences(
            "mySpf",
            Context.MODE_PRIVATE
        )

        // 1. 주소 준비 ( sharedPreference 를 이용해 Fragment4에서 가져온 값 )
        val url = spf.getString("url","http://www.google.com")

        // 2. 설정 변경 ( JavaScript 사용 가능하도록 '허용')
        // manifest permission "Internet"
        // usesCleartextTraffic="true"
        val ws = wv.settings
        ws.javaScriptEnabled = true
        // 3. WebView에 Client 설정
        wv.webViewClient = WebViewClient()
        // 4. 웹 뷰에 주소 적용
        // default Value가 있음!
        wv.loadUrl(url!!)

        return view
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Fragment1().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}