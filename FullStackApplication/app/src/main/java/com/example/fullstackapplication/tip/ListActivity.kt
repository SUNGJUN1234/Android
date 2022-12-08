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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {

    lateinit var adapter:ListAdapter
    // 게시물의 uid값이 들어갈 가변배열
    var keyData = ArrayList<String>()
    // bookmark 경로 설정을 위한 선언
    lateinit var bookmarkRef : DatabaseReference
    // bookmark된 게시물의 정보가 들어갈 배열
    var bookmarkList = ArrayList<String>()

    var auth : FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // realTimeDB에 필요한 객체선언
        // database에 어떤 것을 참조하건지
        val database = Firebase.database
        // Fragment2에서 전체보기를 눌렀을 때 가져올 데이터가 담기는 곳
        val allContent = database.getReference("content")
        // bookmark 경로 설정
        bookmarkRef = database.getReference("bookmarklist")

        // push() :  key 타임스탬프를 찍어줌 (고유한 값을 만들어줌)
//        allContent.push().setValue(
//            ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1248")
//
//        )


        // Fragment2에서 ImageView를 클릭했을 때 넘어와서 구현되어야할 View들
        val list = ArrayList<ListVO>()


        // Firebase에서 데이터를 받아오는 Listener
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapshot",snapshot.toString())
                Log.d("snapshot2",snapshot.value.toString())
                for(model in snapshot.children){
                    val item = model.getValue(ListVO::class.java)
                    // model에는 여러가지 게시물이 담겨있고
                    // 1개에 대한 게시물에 접근하기 위해 value에 접근하고 그 value를 ListVO 형태로 접근
                    if(item !=null){
                        list.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                // 데이터를 받아오는 속도가 adapter가 실행되는 속도보다 느리기 때문에
                // 데이터를 받아온 직후에 새로고침이 필요하다
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        allContent.addValueEventListener(postListener)


        getBookmarkData()




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

        // content
        // uid (게시물을 구분할 수 있는 uid )
        //      imgId
        //      title
        //      url

//            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1248"))
//            list.add(ListVO("https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5516%2F2019%2F05%2F07%2F0000007042_001_20190507183021045.jpg&type=sc960_832","생활팁",R.drawable.bookmark_white,"http://www.dailypop.kr/news/articleView.html?idxno=39012"))
//            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1249"))
//            list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","요리하기",R.drawable.bookmark_white,"https://philosopher-chan.tistory.com/1241"))
//            list.add(ListVO("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA1MjJfNzUg%2FMDAxNjUzMjI5NDM2NzQ2.Y9JI7NWzgoRPk12ppAT81emXpgJJB0UfLdOHvUDBKGMg.g8w3j3uo5SuLxKh6UYw5i71sAuDKmRJ1YGp_XzDKV64g.JPEG.jjaeng_%2F3122112.jpg&type=sc960_832","생활팁",R.drawable.bookmark_white,"https://blog.naver.com/drjanghoneytip/222742217460"))


        // firebase에 값 넣기
//        for(i in 0 until list.size){
//            allContent.push().setValue(
//                list[i]
//            )
//        }

        adapter = ListAdapter(this,
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
            }) as ArrayList<ListVO>,
            keyData,
            bookmarkList
            )

        rvList.adapter = adapter

        rvList.layoutManager = GridLayoutManager(this,2)




    }


    // bookmarklist에 저장되어있는 데이터를 가져오는 함수
    fun getBookmarkData(){

        val postListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // ListActivity를 들릴 때 마다
                // data가 누적 되므로 clear 해주자
                bookmarkList.clear()

                for(model in snapshot.children){
                    Log.d("bookmark",model.toString())
                    Log.d("bookmark",model.value.toString())
                    // 북마크 게시물의 uid값을 bookmarkKist에 저장
//                    bookmarkList.add(model.value.toString())
                    bookmarkList.add(model.key.toString())

                }
                adapter.notifyDataSetChanged()
                Log.d("datasize",bookmarkList.size.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        // bookmarklist 경로에 있는 데이터를 snapshot으로 받아옴
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)
    }





}