package com.example.sjapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import java.util.Random
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    var state:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imgCoin = findViewById<ImageView>(R.id.imgCoin)
        var btnToss = findViewById<Button>(R.id.btnToss)

        btnToss.setOnClickListener {
            var animation :Animation = AnimationUtils.loadAnimation(this,R.anim.coin_effect)
            imgCoin.startAnimation(animation)


                Handler().postDelayed({
                        imgCoin.setImageResource(R.drawable.front2)
                                      },100)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.back2)
            },300)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.front2)
            },500)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.back2)
            },700)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.front2)
            },900)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.back2)
            },1100)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.front2)
            },1200)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.back2)
            },1400)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.front2)
            },1600)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.back2)
            },1800)
            Handler().postDelayed({
                imgCoin.setImageResource(R.drawable.front2)
            },2000)



            Handler().postDelayed({

                state = Random().nextInt(2)

                if(state==1){
                    imgCoin.setImageResource(R.drawable.back2)
                }else{
                    imgCoin.setImageResource(R.drawable.front2)
                }

            },2200)




        }


    }

}