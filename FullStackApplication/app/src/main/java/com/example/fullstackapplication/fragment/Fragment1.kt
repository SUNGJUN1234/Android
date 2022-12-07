package com.example.fullstackapplication.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val clHome = view.findViewById<ConstraintLayout>(R.id.clHome)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)

        val etName = view.findViewById<EditText>(R.id.etName)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        val etContactName = view.findViewById<EditText>(R.id.etContactName)
        val etContactAge = view.findViewById<EditText>(R.id.etContactAge)
        val etContactTel = view.findViewById<EditText>(R.id.etContactTel)
        val btnContact = view.findViewById<Button>(R.id.btnContact)


        // 선생님 firebase로 정보 보내기
        val yehoUrl = "https://android-project-yeho-default-rtdb.firebaseio.com/"
        val yehoDb = Firebase.database(yehoUrl)
        val jsj = yehoDb.getReference("김정우")
        btnSend.setOnClickListener{
            val input = etName.text.toString()
            jsj.setValue(input)
        }

        // firebase 에서 데이터를 실시간으로 읽고 / 쓸 수 있는
        // RealTime DataBase ( NoSQL 형식 : Key/Value 형식 )

        // Write a message to the database
        val database = Firebase.database        // 연결된 firebase 계정의 RealTimeDB를 연결
        val url = "https://full-stack-b550f-default-rtdb.firebaseio.com/"
        val db = Firebase.database(url)

        // 버튼 눌러 RealTimeDataBase에 정보보내기
        val contact = database.getReference("contact2")
        btnContact.setOnClickListener{
            val name = etContactName.text.toString()
            val age = etContactAge.text.toString().toInt()
            val tel = etContactTel.text.toString()

            contact.push().setValue(ContactVO(name,age,tel))
        }


        val myRef = db.getReference("message")
        val color = database.getReference("color")

        myRef.setValue("Hello, World!")

        color.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val bgColor = snapshot.getValue<String>()

                if(bgColor !=null){
                    try{
                        clHome.setBackgroundColor(Color.parseColor(bgColor))
                    }catch (e : IllegalArgumentException){
                        clHome.setBackgroundColor(Color.parseColor("red"))
                    }catch (e : StringIndexOutOfBoundsException){
                        clHome.setBackgroundColor(Color.parseColor("blue"))
                    }
                }else{
                    clHome.setBackgroundColor(Color.parseColor("white"))
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        // 무언가 값이 추가된 이벤트를 감지하는 Listener
        myRef.addValueEventListener(object:ValueEventListener{
            // 데이터가 변하면 동작하는 로직
            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.getValue<String>()

                tvMsg.setText(message)
            }
            // 데이터가 삭제되면 동작하는 로직
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        contact.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val webContact = snapshot.getValue<ContactVO>()
                Log.d("연락처",webContact.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return view
    }

}