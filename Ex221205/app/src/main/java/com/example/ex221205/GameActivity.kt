package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val btnGame = findViewById<Button>(R.id.btnGame)

        val btns = ArrayList<Button>()

        val numbers = ArrayList<Int>()

        // 현재 눌러야 되는 숫자
        var cnt : Int = 1

        rdSet(numbers,cnt)

        while(numbers.size < 25){
            val rdNum = Random.nextInt(25)+1
            if(!numbers.contains(rdNum)){
                numbers.add(rdNum)
            }
        }


        for(i in 1..25){
            val resId = resources.getIdentifier("btn$i","id",packageName)
            val btn = findViewById<Button>(resId)
            btns.add(btn)
            btn.visibility = View.INVISIBLE
        }

        btnGame.setOnClickListener {


            for(i in 0 until  btns.size){
                val  btn = btns.get(i)

                btnSet(btn,numbers.get(i))

                btn.setText(numbers.get(i).toString())
                btn.visibility = View.VISIBLE

                btn.setOnClickListener {
                    if(btn.text.toString().toInt() == cnt){
                        btn.visibility = View.INVISIBLE
                        cnt++

                        if(cnt%25 == 1){
                            rdSet(numbers,cnt)
                            for(j in 0 until btns.size){
                                val btn = btns.get(j)
                                btnSet(btn,numbers.get(j))
                            }
                        }

                    }
                }
            }
        }
    }
    fun btnSet(btn : Button, value:Int){
        btn.setText(value.toString())
        btn.visibility = View.VISIBLE
    }

    fun rdSet(numbers : ArrayList<Int>,cnt : Int){
        numbers.clear()

        for(i in cnt..cnt+24){
            numbers.add(i)
        }
    }
}