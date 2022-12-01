package com.example.ex221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val context:Context,val movies:ArrayList<MovieVO>):RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

        val clMovie:ConstraintLayout

        val tvRank : TextView
        val tvOAN : TextView
        val tvAudi : TextView
        val tvMovieNm : TextView
        val tvOpenDt : TextView

        init{
            tvRank = itemView.findViewById(R.id.tvRank)
            tvOAN = itemView.findViewById(R.id.tvOAN)
            tvAudi = itemView.findViewById(R.id.tvAudi)
            tvMovieNm = itemView.findViewById(R.id.tvMovieNm)
            tvOpenDt = itemView.findViewById(R.id.tvOpenDt)

            clMovie = itemView.findViewById(R.id.clMovie)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.movie_list,null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvMovieNm.setText(movies.get(position).MovieNm)
        holder.tvAudi.setText(movies.get(position).audiAcc)
        holder.tvOAN.setText(movies.get(position).rankOldAndNew)
        holder.tvRank.setText(movies.get(position).rank)
        holder.tvOpenDt.setText(movies.get(position).openDt)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}