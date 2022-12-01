package com.example.ex221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    // Volley에 필요한 객체 2개
    var queue: RequestQueue? = null         // 요청을 가지고 서버로 이동하는 객체
    lateinit var request: StringRequest     // 요청 / 응답이 들어가는 객체

    // VO를 담을 배열 생성
    var movies = ArrayList<MovieVO>()       // 영화정보들이 담길 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val rc = findViewById<RecyclerView>(R.id.rc)
        val btnMovie = findViewById<Button>(R.id.btnMovie)




        // queue 초기화 진행
        // (버튼을 클릭하거나, 에뮬레이터를 작동, OnCreate가 실행될 때마다 request가 들어갈 장소를 만드는 것)
        if(queue==null){
            queue = Volley.newRequestQueue(this@MovieActivity)
        }

        // btnMovie를 클릭 했을 때 영화정보(response안에들어온 정보)를 Log로 확인해보자
        btnMovie.setOnClickListener {

            val url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20221130"

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


                     for(i in 0 until 10){
                         val movie = json3.getJSONObject(i)
                         val rank = movie.getString("rank")
                         val rankOldAndNew = movie.getString("rankOldAndNew")
                         val movieNm = movie.getString("movieNm")
                         val openDt = movie.getString("openDt")
                         val audiAcc = movie.getString("audiAcc")


                         movies.add(MovieVO(rank,rankOldAndNew,movieNm,openDt,audiAcc))
                     }

                     val adapter = MovieAdapter(this,movies)

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