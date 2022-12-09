package com.example.fullstackapplication.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fullstackapplication.R
import com.example.fullstackapplication.utils.FBAuth
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    lateinit var imgLoad : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        // id값 다 찾아오기
        imgLoad = findViewById<ImageView>(R.id.imgLoad)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val imgWrite = findViewById<ImageView>(R.id.imgWrite)


        // 갤러리로 이동해서 이미지를 받아오는 기능
        imgLoad.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            // 양방향 데이터 전송을 하려면 launcher를 사용하자
            launcher.launch(intent)
        }


        // 모든 값을 Firebase에 저장시켜주는 버튼
        imgWrite.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            // Firebase 에 들어갈 형태
            // board
            //      - key (게시물의 고유한 uid : push())
            //          - boardVO( title, content, 사용자 uid, time )


            // 현재 사용자의 uid값을 가져오려면 auth를 이용해야한다
            // FBAuth 클래스에 만들어놓은 getUid를 사용하자
            val uid = FBAuth.getUid()
            // 시간을 가져오자
            val time = FBAuth.getTime()

            // setValue가 되기전에 미리 BoardVO가 저장될 key값을 설정(uid값을 만들어 저장하자)
            // 이미지의 이름을 해당 게시물의 uid값과 맞추기 위해
            var key = FBdatabase.getBoardRef().push().key.toString()

            FBdatabase.getBoardRef().child(key).setValue(BoardVO(title,content,uid,time))

            imgUpload(key)

            // 현재 Activity 종료시키기
            finish()
        }

    }

    // 양방향 데이터 전송을 위한 launcher
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        // 여기서 it에는 resultCode와 data가 함께 담겨온다
        if(it.resultCode == RESULT_OK){
        imgLoad.setImageURI(it.data?.data)
        }

    }
    // storage에 이미지를 저장해도 realtimeDB에 저장되어있는 해당 제목과 내용이 있는 게시물과 매칭 할 수 없다
    // 왜? 이미지는 해당 이미지의 uid만 가지고 해당 게시물의 uid값은 가지지 않기 때문
    // 이것을 해결하기위해 해당 개시물의 uid값을 해당 게시물의 uid값과 함쳐서 만들어줘야한다
    fun imgUpload(key : String){

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("$key.png")

        imgLoad.isDrawingCacheEnabled = true
        imgLoad.buildDrawingCache()
        val bitmap = (imgLoad.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        // bitmap.compress(압축할 파일의 타입 , 압축의 퀄리티(1~100) , baos)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
        }
    }


}













