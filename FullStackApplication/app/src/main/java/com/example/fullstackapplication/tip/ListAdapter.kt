package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListAdapter(val context : Context, val list : ArrayList<ListVO>,val keyData : ArrayList<String> , val bookmarkList : ArrayList<String>)
    :RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val database = Firebase.database
    val auth : FirebaseAuth = Firebase.auth

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgMain : ImageView
        val tvText : TextView
        val imgBook : ImageView

        init{
            imgMain = itemView.findViewById(R.id.imgMain)
            tvText = itemView.findViewById(R.id.tvText)
            imgBook = itemView.findViewById(R.id.imgBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate : xml코드를 눈에 보이는 View객체로 바꿔서 ViewHolder로 보내주는 역할
        // getSystemServicce : 하드웨어가 가지고 있는 많은 센서 서비스ㄹ들이 담겨있음
        // 상황에 맞게 위의 2개중 하나를 이용해서 xml코드를 View객체로 바꿔주자
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_template, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.imgMain.setImageResource(list.get(position).imgMain)
        holder.tvText.setText(list.get(position).tvText)
        holder.imgBook.setImageResource(list.get(position).imgBook)

        Glide.with(context)
            .load(list[position].imgMain)
            .into(holder.imgMain)

        // imgView를 클릭했을 때
        // url값을 가지고 WebViewActivity로 넘어간다!!
        holder.imgMain.setOnClickListener{

            val spf =  context.getSharedPreferences(
                "url",
                Context.MODE_PRIVATE
            )
            var editor = spf.edit()
            editor.putString("url",list.get(position).url)
            editor.commit()

            context.startActivity(Intent(context,WebViewActivity::class.java))
        }

        // 클릭을 했을 때 색깔을 바꾸면 기존에 있던 북마크는 색이 안칠해져있음
        // adapter가 실행이 되는 순간 북마크로 있던 데이터들은 바로 색칠될 수 있게
        if(bookmarkList.contains(keyData[position])){
            holder.imgBook.setImageResource(R.drawable.bookmark_color)
        }else{
            holder.imgBook.setImageResource(R.drawable.bookmark_white)
        }



        // 북마크 모양의 이미지를 클릭했을 때
        // 해당 게시물의 uid 값이 bookmarklist 경로로 들어가야함
        holder.imgBook.setOnClickListener{

            // Firebase에 있는 bookmarklist경로로 접근
            val bookmarkRef = database.getReference("bookmarklist")

            // 여기까지 왔다는건 로그인이 되었다는거니까 "!!"
            bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")

            // 이미 저장이 되어있는 게시물인지 아닌지 확인
            // how? : bookmarkList에 해당 게시물이 포함되어있는지 판단
            if(bookmarkList.contains(keyData[position])){
                // 포함되어있다면 북마크를 취소
                // database에서 해당 keyData를 삭제
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()
               // holder.imgBook.setImageResource(R.drawable.bookmark_white)

            }else{
                // 포함되어있지 않다면 북마크를 추가
                // database에서 해당 keyData를 추가
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")
               // holder.imgBook.setImageResource(R.drawable.bookmark_color)

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}