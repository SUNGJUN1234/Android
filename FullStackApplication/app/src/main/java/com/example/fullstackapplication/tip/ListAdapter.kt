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

class ListAdapter(val context : Context, val list : ArrayList<ListVO>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {


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




    }

    override fun getItemCount(): Int {
        return list.size
    }

}