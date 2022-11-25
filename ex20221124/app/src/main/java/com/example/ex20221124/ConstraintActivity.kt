package com.example.ex20221124

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

// : = Kotlin에서 상속을 의미
class ConstraintActivity : AppCompatActivity() {

    // 전역변수 뷰 만들기
    // onCreate 바깥이라서 findViewById는 사용할 수 없다
    // 뷰에 대해서는 선언만 하는 것은 불가능하다(초기화가 꼭 이루어져야한다)
    // lateinit 이라는 키워드로 나중에 꼭 초기화하겠다는 약속을 할 수 있다
    lateinit var tvResult : TextView
    lateinit var btnPlus : Button
    lateinit var btnMinus : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var etNum1 : EditText
    lateinit var etNum2 : EditText



    // onCreate()  =  Activity가 실행될 때 최초 딱 한번(가장 먼저) 호출되는 메서드
    // : Activity 생명주기
    override fun onCreate(savedInstanceState: Bundle?) {
        // savedInstanceState: Bundle? = 가로모드에서 세로모드로 바꿨을 때 데이터를 유지시켜주는 기능
        super.onCreate(savedInstanceState)
        // ***** xml 과 kotlinClass 를 연결하는 코드 없으면 화면이 뜨지 않는다 *****
        setContentView(R.layout.activity_constraint)

        // 1. xml의 View에 id를 지정
        // 2. id값을 이용해서 view를 찾아온다 (findViewById)
        // ** findViewById는 setContentView 위로 올리면 안된다 **
        tvResult = findViewById<TextView>(R.id.tvResult)
        btnPlus = findViewById<Button>(R.id.btnPlus)
        btnMinus = findViewById<Button>(R.id.btnMinus)
        btnMul = findViewById<Button>(R.id.btnMul)
        btnDiv = findViewById<Button>(R.id.btnDiv)
        etNum1 = findViewById<EditText>(R.id.etNum1)
        etNum2 = findViewById<EditText>(R.id.etNum2)


        // R = 리소스 폴더를 의미한다
        // 내가 String타입으로 저장한 아이디인 tvResult는
        // R폴더의 id폴더 내에 String타입이 아닌 주소값(16진수)으로 저장된다

        // 텍스트 색깔 바꾸기
        tvResult.setTextColor(Color.BLUE)
        tvResult.setTextColor(Color.parseColor("#ff9999"))

        // 텍스트 크기 바꾸기
        // tvResult.textSize  =  get / set 효과 둘 다 가지고 있다
        // textSize에는 float자료형이 들어가야 한다! ( f: 형변환 )
        tvResult.textSize = 40.0f

        // 텍스트 내용 바꾸기
        // CharSequence = String이 상속받는 인터페이스
        tvResult.text = "안녕하세요"



        /////////////////////////////////////////////////////////
        // 더하기 버튼을 눌렀을 때 "더하기 버튼이 눌렸습니다" 라는
        // Toast 를 띄워주자

        // 버튼에 이벤트 넣는 3가지 방법



        // 1) innerClass ( Listener OnClick 구현 )

        btnMinus.setOnClickListener {
            // {}안에다가 기능 구현만 하면 됨!
            // "마이너스 버튼이 눌렸습니다" 라는 토스트창 띄우기
            Toast.makeText(this,"마이너스 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()

            // 버튼이 눌렸을 때 해야할 일
            // 1.EditText에 적혀있는 getText 는 리턴타입이 Editable
            // 해당 값을 담아주고 싶다면 문자열로 형변환 시켜줘야한다
            // 만약 산술연산을 하고싶다면 정수형으로 형변환 시켜줘야한다
            // 2. num1, num2 연산 결과를 tvResult에 set해주자
            // ( num1, num2 연산 결과를 문자열로 바꿔서 set 해주자 )

            var num1: Int = etNum1.text.toString().toInt()
            var num2: Int = etNum2.text.toString().toInt()

            tvResult.text = "연산결과 : "+(num1-num2).toString()

        }
        btnMul.setOnClickListener {
            var num1: Int = etNum1.text.toString().toInt()
            var num2: Int = etNum2.text.toString().toInt()

            tvResult.text = "연산결과 : "+(num1*num2).toString()
        }
        btnDiv.setOnClickListener {
            var num1: Int = etNum1.text.toString().toInt()
            var num2: Int = etNum2.text.toString().toInt()

            tvResult.text = "연산결과 : "+(num1/num2).toString()
        }



    } // oncreate 함수 경계



    // 2) 이벤트 메소드 설계 후 연결하기

    // 이벤트 리스너는 무조건 View 매개변수를 가지고 있어야한다
    fun myClick(view: View){
        // Toast 띄우기
        // 매개변수 1) Toast를 띄울 화면 정보 : this
        // 대신 사용가능한 것으로는 ConstraintActivity.this
        // 매개변수 2) 어떤 문구를 띄울지 : ( 무조건 String , Int가 허용되는 경우는 id값만 )
        // 매개변수 3) 얼마나 띄울건지 : 3초(short) or 5초(long)
        Toast.makeText(this,"더하기 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()
        // 토스트를 만들었으면 보여지게 만들어야한다 : .show()

        var num1 = etNum1.text.toString().toInt()
        // 위의 함수를 클릭함수 밖에 놔둔다면
        // num1에는 아무런 값도 없어지므로 ""값에 .toInt()를 실핼시키게 되고
        // NumberFormatException 오류가 발생하게 된다
        var num2= etNum2.text.toString().toInt()
        var result = num1 + num2
        tvResult.text = "연산결과 : $result"
    }

    // 3) inerface를 상속받게 만들어서 OnClick 구현


}