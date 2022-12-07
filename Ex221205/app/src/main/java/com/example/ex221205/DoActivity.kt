package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class DoActivity : AppCompatActivity() {

    var isPlaying : Boolean = true
    var score : Int = 0     // 점수를 저장하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnStart = findViewById<Button>(R.id.btnStart)

        val imgViews = ArrayList<ImageView>()



        val tvTime = findViewById<TextView>(R.id.tvTime)


        for(i in 1..9){

            val resId = resources.getIdentifier("imageView"+i.toString(),"id",packageName)
            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            imgView.visibility = View.INVISIBLE
        }

        btnStart.setOnClickListener {

            val timeThread = TimeThread(tvTime)
            timeThread.start()

            for(i in 0 until imgViews.size){
                val imgView = imgViews.get(i)
                imgView.visibility = View.VISIBLE

                // 각 이미지 뷰의 tag는 최초 0이다
                // 0은 두더지가 앉아있음을 의미한다
                imgView.tag = 0

                val thread = DoThread(imgView)
                thread.start()

                imgView.setOnClickListener{
                    if(imgView.tag == 1){
                        score++
                    }else{
                        score--
                        if(score<0){
                            score = 0
                        }
                    }
                    tvScore.setText("현재 점수 : $score")
                }
            }
        }
    }       // onCreate 경계

    val timeHandler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView
            tv.setText(time.toString())
        }
    }


    inner class TimeThread(val tv : TextView) : Thread(){
        override fun run() {
            super.run()

            for(i in 30 downTo 0){
                val message = Message()
                message.arg1 = i
                message.obj = tv
                timeHandler.sendMessage(message)
                Thread.sleep(1000)

            }
            isPlaying = false
//            val intent = Intent(this@DoActivity,MainActivity::class.java)
//            startActivity(intent)
        }
    }



    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val imgView = msg.obj as ImageView  // 어떤 이미지뷰에 적용될 건지??
            val img = msg.arg1                  // on이미지인지? off이미지인지?
            val tag = msg.arg2                  // 현재 이미지의 상태! 1 이라면 일어남 , 0 이라면 앉아 있음
            imgView.setImageResource(img)
            imgView.tag = tag
        }
    }

    inner class DoThread(val imgView : ImageView) : Thread(){
        override fun run() {
            super.run()



        while(isPlaying) {

            var level = score * 20
            if(score >=40){
                level = 800
            }

            // 랜덤하게 0~5초 후에 일어나게!
            val onTime = Random.nextInt(5*(1000-level))

            Thread.sleep(onTime.toLong())

            val onMessage = Message()
            onMessage.arg1 = R.drawable.on
            onMessage.arg2 = 1      // 두더지가 일어나 있음을 의미
            onMessage.obj = imgView
            handler.sendMessage(onMessage)


            // 랜덤하게 0~5초 후에 일어나게!
            val offTime = Random.nextInt(3*(1000-level))

            Thread.sleep(offTime.toLong())

            val offMessage = Message()
            offMessage.arg1 = R.drawable.off2
            offMessage.arg2 = 0     // 두더지가 앉아 있음을 의미
            offMessage.obj = imgView
            handler.sendMessage(offMessage)
        }

        }
    }


}