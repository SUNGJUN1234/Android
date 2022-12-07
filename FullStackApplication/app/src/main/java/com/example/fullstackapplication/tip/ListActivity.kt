package com.example.fullstackapplication.tip

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        // Fragment2에서 ImageView를 클릭했을 때 넘어와서 구현되어야할 View들

        val list = ArrayList<ListVO>()

        val tvList = findViewById<TextView>(R.id.tvList)
        val rvList = findViewById<RecyclerView>(R.id.rvList)

        val spf =getSharedPreferences(
            "mySpf",
            Context.MODE_PRIVATE
        )

        val category = spf.getString("category","??")

//
        val categoty2 = intent.getStringArrayExtra("category")
//
        tvList.text = category

            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1248"))
            list.add(ListVO("https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5516%2F2019%2F05%2F07%2F0000007042_001_20190507183021045.jpg&type=sc960_832","생활팁",R.drawable.bookmark_white,"http://www.dailypop.kr/news/articleView.html?idxno=39012"))
            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1249"))
            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1241"))
            list.add(ListVO("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA1MjJfNzUg%2FMDAxNjUzMjI5NDM2NzQ2.Y9JI7NWzgoRPk12ppAT81emXpgJJB0UfLdOHvUDBKGMg.g8w3j3uo5SuLxKh6UYw5i71sAuDKmRJ1YGp_XzDKV64g.JPEG.jjaeng_%2F3122112.jpg&type=sc960_832","생활팁",R.drawable.bookmark_white,"https://blog.naver.com/drjanghoneytip/222742217460"))



        val adapter = ListAdapter(this,
            (if(category == "전체보기"){
                list
            }else if(category == "요리레시피"){
                list.filter{
                    it.tvText === "요리하기"
                }
            }else{
                list.filter {
                    it.tvText === "생활팁"
                }
            }) as ArrayList<ListVO>
            )

        rvList.adapter = adapter

        rvList.layoutManager = GridLayoutManager(this,2)



    }
}