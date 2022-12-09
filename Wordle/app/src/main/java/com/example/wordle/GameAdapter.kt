package com.example.wordle

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(val context: Context, val data : ArrayList<GameVO>, val answer:String):RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val etGame1 : EditText
        val etGame2 : EditText
        val etGame3 : EditText
        val etGame4 : EditText
        val etGame5 : EditText


        init {

            etGame1 = itemView.findViewById(R.id.etGame1)
            etGame2 = itemView.findViewById(R.id.etGame2)
            etGame3 = itemView.findViewById(R.id.etGame3)
            etGame4 = itemView.findViewById(R.id.etGame4)
            etGame5 = itemView.findViewById(R.id.etGame5)

            val etList = ArrayList<EditText>()
            etList.add(etGame1)
            etList.add(etGame2)
            etList.add(etGame3)
            etList.add(etGame4)
            etList.add(etGame5)



            etGame1.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if(etGame1.length()==1){
                        etGame2.requestFocus()
                    }
                    return false
                }
            })

            etGame2.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if(etGame2.length()==1){
                        etGame3.requestFocus()
                    }
                    return false
                }
            })
            etGame3.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if(etGame3.length()==1){
                        etGame4.requestFocus()
                    }
                    return false
                }
            })
            etGame4.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if(etGame4.length()==1){
                        etGame5.requestFocus()
                    }
                    return false
                }
            })




            etGame5.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    Log.d("test1",p1.toString())
                    Log.d("test2",p2.toString())
                    // 66은 엔터를 의미한다
                    if(p1 == 66 && p2?.action == KeyEvent.ACTION_UP){
                        Log.d("test3","엔터눌러짐")
                        // 정답 여부에 따라 색깔을 변환하는 코드
                        checkAnswer(answer,etList)
                        etGame1.requestFocus()

                        if(adapterPosition == data.size-1){
                        Toast.makeText(context,"정답 : "+answer, Toast.LENGTH_SHORT).show()
                        }
                        return true
                    }
                    return false
                }

            })






        }
    }
// =====선생님 로직======
//fun checkAnswer (answer : String, etList : ArrayList<EditText>){
//    for(i in 0 until etList.size){
//        val answerChar : Char = answer.get(i)
//        val etChar : Char = etList.get(i).text.toString().single()
//        if(answerChar == etChar){
//            etList.get(i).setBackgroundColor(Color.parseColor("green"))
//        }else{
//            var check = true
//            for(j in 0 until etList.size){
//                if(etChar == answer.get(j)){
//                    etList.get(i).setBackgroundColor(Color.parseColor("yellow"))
//                    check = false
//                }
//            }
//            if(check){
//                etList.get(i).setBackgroundColor(Color.parseColor("gray"))
//            }
//        }
//    }
//}
// =======================


    fun checkAnswer (answer : String, etList : ArrayList<EditText>){

        for(i in 0 until etList.size){
            etList.get(i).setBackgroundResource(R.drawable.text_fail)
            etList.get(i).setClickable(false)
            etList.get(i).setFocusable(false)

            // etList.get(i).isEnable = false
        }

        for(i in 0 until etList.size){
            for(j in 0 until etList.size){
                if(answer.get(j) == etList.get(i).text.toString().single()){
                etList.get(i).setBackgroundResource(R.drawable.text_draw)
                }
            }
        }

        for(i in 0 until etList.size){
            if(answer.get(i) == etList.get(i).text.toString().single()){
                etList.get(i).setBackgroundResource(R.drawable.text_success)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.game_list,null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etGame1.setText(data[position].word1)
        holder.etGame2.setText(data[position].word2)
        holder.etGame3.setText(data[position].word3)
        holder.etGame4.setText(data[position].word4)
        holder.etGame5.setText(data[position].word5)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}