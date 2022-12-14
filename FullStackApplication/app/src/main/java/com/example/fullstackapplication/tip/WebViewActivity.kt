package com.example.fullstackapplication.tip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // 받아온 url값을 꺼내
        // 해당 웹페이지가 WebView에 뜨게 만들자

        val wv = findViewById<WebView>(R.id.wv)

        val spf = getSharedPreferences(
            "url",
            Context.MODE_PRIVATE
        )

        // WebView에 원하는 웹페이지 띄우기
        // 1. 주소준비
        // getString은 Strinf? 타입으로 받아온다
        val url = spf.getString("url","http://www.google.com")
        // 2. 설정변경( JavaScript 지원 )
        val ws = wv.settings
        ws.javaScriptEnabled = true
        // 3. WebView에 Client 설정!
        wv.webViewClient = WebViewClient()
        // 4. 주소 적용
        // intent로 부터 값을 무조건 받아오는 이유 :
        // 값이 없으면 RecyclerView에서 보이지 않았을 테니
        // 여기까지 도달했다는건 값이 있다는 것
        wv.loadUrl(url!!)

    }
}