package com.example.ex221131

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2(val context:Context, val pokeList: ArrayList<PokeVO>) : RecyclerView.Adapter<PokeAdapter2.ViewHolder2>(){

    // Java에서는 OnClickEvent를 구현한다! (Interface형태로)

    // inner class를 사용하려면 inner class를 명시해줘야한다
    // outer class의 member에 접근할 수 있다
    inner class ViewHolder2(itemView:View) : RecyclerView.ViewHolder(itemView){

        val imgPoke : ImageButton
        val tvPokeLevel : TextView
        val tvPokeName : TextView
        val tvPokeType : TextView

        init{
            imgPoke = itemView.findViewById<ImageButton>(R.id.imgPoke)
            tvPokeLevel = itemView.findViewById<TextView>(R.id.tvPokeLevel)
            tvPokeName = itemView.findViewById<TextView>(R.id.tvPokeName)
            tvPokeType = itemView.findViewById<TextView>(R.id.tvPokeType)

            // 이벤트 구현
            // 1. ListView의 setOnItemClickListener
            itemView.setOnClickListener{
                // 해당 아이템의 position 정보가 필요함! -> adapterPosition
                val position:Int = adapterPosition     // 내가 클릭한 itemView의 위치 (밖으로 빼서 전역변수 설정 X)
                pokeList.removeAt(position)
                notifyDataSetChanged()                 // 새로고침
            }

            imgPoke.setOnClickListener{

                val position:Int = adapterPosition
                // 피카츄 -> 피카츄입니다!!
                // Toast.makeText(context,pokeList.get(position).name+"입니다.",Toast.LENGTH_SHORT).show()

                // "Lv :1 / 피카츄 / 타입 : 전기"
                val level: String = pokeList.get(position).level
                val name : String = pokeList.get(position).name
                val type : String = pokeList.get(position).type

                val result:String = "LV : ${level}/${name}/타입 : ${type}"
                Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
            }

        }

    }

    // itemView가 없을 때, ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {

        val layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.poke_list, null)

        return ViewHolder2(view)
    }

    // 만들어진 ViewHolder가 있다면, 꺼내서 쓰는 곳!
    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {

        holder.imgPoke.setImageResource(pokeList.get(position).img)
        holder.tvPokeLevel.setText(pokeList.get(position).level)
        holder.tvPokeName.setText(pokeList.get(position).name)
        holder.tvPokeType.setText(pokeList.get(position).type)

    }

    override fun getItemCount(): Int {
        return pokeList.size
    }


}