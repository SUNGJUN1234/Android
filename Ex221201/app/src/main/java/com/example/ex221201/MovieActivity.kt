package com.example.ex221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    // Volley에 필요한 객체 2개
    var queue: RequestQueue? = null         // 요청을 가지고 서버로 이동하는 객체 (FIFO)
    lateinit var request: StringRequest     // 요청 / 응답이 들어가는 객체

    // VO를 담을 배열 생성
    var movies = ArrayList<MovieVO>()       // 영화정보들이 담길 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        
        // Volley 를 통한 네트워크 통신 4단계
        // 1. Volley 설정
        // - Volley 라이브러리 추가
        // - Manifest 에 permission 추가!! (Internet)
        // (혹시나 나중에 permission을 추가했다면 어플 삭제 후 다시 실행)
        // 2. RequestQueue 생성
        // 3. Request 생성
        // 4. RequestQueue 에 Request 추가
        
        // RecyclerView 6단계
        // 1) Container 설정
        val rc = findViewById<RecyclerView>(R.id.rc)
        val btnMovie = findViewById<Button>(R.id.btnMovie)
        val etInput = findViewById<EditText>(R.id.etInput)

        // 2) Template 설정
        // movie_list.xml

        // 3) Item 설정
        // movies : ArrayList<MovieVO>


        // queue 초기화 진행
        // (버튼을 클릭하거나, 에뮬레이터를 작동, OnCreate가 실행될 때마다 request가 들어갈 장소를 만드는 것)
        if(queue==null){
            queue = Volley.newRequestQueue(this@MovieActivity)
        }

        // btnMovie를 클릭 했을 때 영화정보(response안에들어온 정보)를 Log로 확인해보자
        btnMovie.setOnClickListener {

            val date = etInput.text.toString()
            val url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=${date}"

            // StringRequest의 4가지 매개인자
            // request = StringRequest(1,2,3,4)     //  -> 매개인자를 까먹었다면 1,2,3,4 를 써보자
             request = StringRequest(
                 // 1. get/post
                 Request.Method.GET,
                 // 2. URL
                 url,
                 {response->
                     // 응답받아온 response : String => json 형식으로 바꾸자
                     val json1 = JSONObject(response)
                     val json2 = json1.getJSONObject("boxOfficeResult")
                     val json3 = json2.getJSONArray("dailyBoxOfficeList")
                     Log.d("json3",json3.toString())

                     movies.clear()

                     for(i in 0 until 10){
                         val movie = json3.getJSONObject(i)
                         val rank = movie.getString("rank")
                         val rankOldAndNew = movie.getString("rankOldAndNew")
                         val movieNm = movie.getString("movieNm")
                         Log.d("영화",movieNm)
                         val openDt = movie.getString("openDt")
                         val audiAcc = movie.getString("audiAcc")


                         movies.add(MovieVO(rank,rankOldAndNew,movieNm,openDt,audiAcc))
                     }

                     // 4) Adapter 생성
                     val adapter = MovieAdapter(this,movies)

                     // 5) Container 에 Adapter 붙이기
                     rc.adapter = adapter

                     rc.layoutManager = LinearLayoutManager(this)

                     adapter.notifyDataSetChanged()
                 },
                 { error->
                     Log.d("error",error.toString())
                 }
             )

            queue?.add(request)

        }




    }
}