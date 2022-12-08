package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var avFall = findViewById<LottieAnimationView>(R.id.avFall)
        var btnLottery = findViewById<LottieAnimationView>(R.id.btnLottery)
        var number1 = findViewById<TextView>(R.id.number1)
        var number2 = findViewById<TextView>(R.id.number2)
        var number3 = findViewById<TextView>(R.id.number3)
        var number4 = findViewById<TextView>(R.id.number4)
        var number5 = findViewById<TextView>(R.id.number5)
        var number6 = findViewById<TextView>(R.id.number6)


        val lotteryNumbers = arrayListOf(number1,number2,number3,number4,number5,number6)


        val countDownTimer1 = object : CountDownTimer(1000,50){
            override fun onTick(p0: Long) {
                val randomNumber = Random.nextInt(45)+1
                number1.text = randomNumber.toString()

            }
            override fun onFinish() {
            }
        }
        val countDownTimer2 = object : CountDownTimer(2000,50){
            override fun onTick(p0: Long) {

                var randomNumber = Random.nextInt(45)+1
                while(randomNumber.toString() == number1.text.toString()){
                    randomNumber = Random.nextInt(45)+1
                }

                number2.text = randomNumber.toString()

            }
            override fun onFinish() {
            }
        }

        val countDownTimer3 = object : CountDownTimer(3000,50){
            override fun onTick(p0: Long) {
                var randomNumber = Random.nextInt(45)+1
                while(randomNumber.toString() == number1.text.toString()&&randomNumber.toString() == number2.text.toString()){
                    randomNumber = Random.nextInt(45)+1
                }

                number3.text = randomNumber.toString()

            }
            override fun onFinish() {
            }
        }
        val countDownTimer4 = object : CountDownTimer(4000,50){
            override fun onTick(p0: Long) {
                var randomNumber = Random.nextInt(45)+1
                while(randomNumber.toString() == number1.text.toString()&&randomNumber.toString() == number2.text.toString()
                    &&randomNumber.toString() == number3.text.toString()){
                    randomNumber = Random.nextInt(45)+1
                }
                number4.text = randomNumber.toString()

            }
            override fun onFinish() {
            }
        }

        val countDownTimer5 = object : CountDownTimer(5000,50){
            override fun onTick(p0: Long) {
                var randomNumber = Random.nextInt(45)+1
                while(randomNumber.toString() == number1.text.toString()&&randomNumber.toString() == number2.text.toString()
                    &&randomNumber.toString() == number3.text.toString()&&randomNumber.toString() == number4.text.toString()){
                    randomNumber = Random.nextInt(45)+1
                }
                number5.text = randomNumber.toString()
            }
            override fun onFinish() {
            }
        }

        val countDownTimer6 = object : CountDownTimer(6000,50){
            override fun onTick(p0: Long) {
                var randomNumber = Random.nextInt(45)+1
                while(randomNumber.toString() == number1.text.toString()&&randomNumber.toString() == number2.text.toString()
                    &&randomNumber.toString() == number3.text.toString()&&randomNumber.toString() == number4.text.toString()
                    &&randomNumber.toString() == number5.text.toString()){
                    randomNumber = Random.nextInt(45)+1
                }
                number6.text = randomNumber.toString()

            }
            override fun onFinish() {
                btnLottery.cancelAnimation()
                avFall.playAnimation()
            }
        }

        btnLottery.setOnClickListener{
//            if(btnLottery.isAnimating) {
//                btnLottery.cancelAnimation()
//                countDownTimer1.cancel()
//                countDownTimer2.cancel()
//                countDownTimer3.cancel()
//                countDownTimer4.cancel()
//                countDownTimer5.cancel()
//                countDownTimer6.cancel()
//            }else{
                btnLottery.playAnimation()
                countDownTimer1.start()
                countDownTimer2.start()
                countDownTimer3.start()
            countDownTimer4.start()
            countDownTimer5.start()
            countDownTimer6.start()
//            }

        }


    }
}