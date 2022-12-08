package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

// fragment4번용 Adapter
class BookmarkAdapter(val context:Context, var data : ArrayList<ListVO>, var keyData : ArrayList<String>, var bookmarkList : ArrayList<String> )
    : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
            val imgMain : ImageView
            val tvText : TextView
            val imgBook : ImageView

            init {
                imgMain = itemView.findViewById(R.id.imgMain)
                tvText = itemView.findViewById(R.id.tvText)
                imgBook = itemView.findViewById(R.id.imgBook)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // list_template.xml 을 눈에 보이는 View객체로 바꿔주자
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_template,null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 북마크 데이터에 포함되어 있는지를 판단하여
        // view 와 data 를 합쳐주는 작업을 진행
        if(bookmarkList.contains(keyData[position])){
            holder.tvText.text = data[position].tvText
            Glide.with(context)
                .load(data[position].imgMain)
                .into(holder.imgMain)
            // Glide : 웹에 있는 이미지를 가져와서 세팅해주는 기능
            // imgMain ---> 이미지의 주소 값이 들어가있는 상태
        }
        if(bookmarkList.contains(keyData[position])){
            holder.imgBook.setImageResource(R.drawable.bookmark_color)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}