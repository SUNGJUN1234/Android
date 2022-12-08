package com.example.fullstackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ContactAdapter
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Fragment5 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_5, container, false)

        // AdapterView 6단계
        // 1. Container 결정
        val rvContact = view.findViewById<RecyclerView>(R.id.rvContact)

        // 2. Teamplate 결정
        // contact_list.xml

        // 3. Item 결정
        // ContactVO
        val contactList = ArrayList<ContactVO>()

        // 4. Adapter 생성 / 결정
        // ***** Activity가 아닌곳에서 페이지 정보불러오기 !!! requireContext *****
        val adapter = ContactAdapter(requireContext(),contactList)


        // FireBase_RealTimeDB로 정보넣기
        // 내가 json파일을 가지고있는 프로젝트와 연동
        val db = Firebase.database
        // 경로만들기
        val contact2 = db.getReference("contact2")
        // 그 경로의 하위 값이 변하는걸 감지하는 리스너 생성 (빨간줄 그어진 object 누르고 오버라이딩 해주기)
        contact2.addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // 변한 하나하나의 정보에 접근하기
                val contact = snapshot.getValue<ContactVO>() as ContactVO
                // 해당 정보를 배열에 넣기
                contactList.add(contact)
                // 추가가 완료됐다면 어댑터 새로고침!
                adapter.notifyDataSetChanged()

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

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


        // 5. Container 에 Adapter 부착
        rvContact.adapter = adapter
        rvContact.layoutManager = LinearLayoutManager(requireContext())

        // onCreateView안에서 사용할 addChildEventListener를 만들기


        return view
    }



}