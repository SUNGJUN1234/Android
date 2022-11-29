package com.example.ex20221128

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)


        // 암묵적 intent
        // : 안드로이드 내부에 있는 어플리케이션을 실행
        // Chrome, Camera,Message, Call

        // Intent의 사용용도
        // 1-1. 액션, 데이터
        // 1-2. 액션 -> Camera
        btnCall.setOnClickListener {
            // btnCall 을 누려면 전화가 가게 만들어 보자
            // 데이터 : 전화번호

            // 데이터(URI) = key: value
            // 전화번호라면 key가 "tel : 010-1234-5678"
            var uri = Uri.parse("tel:010-1234-5678")

            var intent = Intent(Intent.ACTION_CALL, uri)

            // permission : 권한
            // 사용자에게 권한을 줄건지 물어봐 줘야함
            // ActivityCompat
            // checkSelfPermission() : 현재 권한이 부여되어있는지
            // (현재 페이지 정보, 어떤 권한인지)
            // 결과값으로 승인이 되어있는지? 안되었는지 받아오기
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
                // 승인이 안되어있는 상태라면 알림창을 띄워서 승인할 수 있도록하자

                // ActivityCompat에는 확인하는 기능 요청하는 기능이 둘 다 들어가있다
                ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                    0
                )
                // requestCode : 내가 뭘 요청한건지 구분하기위한 숫자 (구분만 된다면 어떤 수라도 상관없음 )
                return@setOnClickListener
            }

            // intent 실행시키기
            startActivity(intent)
        }

        // btnWeb을 클릭하면 구글 홈페이지가 보이게 만들자
        btnWeb.setOnClickListener {
            // 데이터 : 구글 주소 ( http://www.google.co.kr )
            var uri = Uri.parse("http://www.google.co.kr")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // btnGoogle을 클릭하면 구글 맵을 보이게 만들어 주자
        btnGoogle.setOnClickListener {
            // 데이터 : 구글 맵은 get방식 (쿼리스트링 )
            // naver.com ? keyword = '안드로이드'
            // 구글 맵 주소?경도,위도
            var uri = Uri.parse("http://google.com/maps?q=35.14670147841655,126.92215633785938\"")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // btnSearch를 클릭하면 해당 키워드로 구글 검색
        btnSearch.setOnClickListener {
            // 1. 검색하는 intent를 하나 생성한다( 우선은 액션만 넣어주자 )
            var intent = Intent(Intent.ACTION_WEB_SEARCH)
            // 2. 검색하고 싶은 키워드를 인텐트에 넣어준다
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            // 3. intent 실행
            startActivity(intent)
        }

        // btnSms를 클릭하면 문자를 보내는 페이지로 이동한 다음 내용을 꺼내 올 예정
        btnSms.setOnClickListener {
            var intent = Intent(Intent.ACTION_SENDTO)
            // 데이터 : 문자내용 , 누구에게 보낼건지? (tel: ---> uri 파싱 필요)
            // 문자내용
            // "sms_body" 라는 key값이 value가 문자내용임을 구분할 수 있다
            intent.putExtra("sms_body","안녕하세요?")
            // 누구에게?
            intent.data = Uri.parse("smsto:"+Uri.encode("010-1234-5678"))
            startActivity(intent)
        }


        // btnPhoto 클릭했을 때 사진 찍기
        // MediaStore : Emulator에서 동작할 수 있는 카메라, 저장소
        btnPhoto.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }




        // 2. Android 4대 구성요소 간의 데이터 주고받을 때

    }
}