package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardAdapter
import com.example.fullstackapplication.board.BoardInsideActivity
import com.example.fullstackapplication.board.BoardVO
import com.example.fullstackapplication.board.BoardWriteActivity
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Fragment3 : Fragment() {

    // getBoardData를 통해 받아온 item(BoardVO)을 관리하는 배열
    var boardList = ArrayList<BoardVO>()
    lateinit var adapter : BoardAdapter

    // 이미지 띄우기를 위한 key값을 저장할 배열
    var keyData = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // btnWrite를 클릭하면 BoardWriteActivity로 이동
        val view = inflater.inflate(R.layout.fragment_3, container, false)
        val boardRv = view.findViewById<RecyclerView>(R.id.boardRv)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)

        btnWrite.setOnClickListener {
            val intent = Intent(requireActivity(),BoardWriteActivity::class.java)
            startActivity(intent)
        }

        // 1. 한칸에 들어갈 디자인 만들기 (board_list.xml)
        // board_template

        // 2. Adapter에 보낼 데이터 가져오기
        // Firebase 의 board 경로에 있는 데이터를 가져오기
        getBoardData()

        // 3. Adapter 만들기 ( data )
        adapter = BoardAdapter(requireContext(),boardList)
        
        // Adapter 에서 만든 항목 클릭이벤트 호출
        adapter.setOnItemClickListener(object : BoardAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                // BoardInsideActivity로 넘어가자
                val intent = Intent(requireActivity(),BoardInsideActivity::class.java)
                intent.putExtra("title",boardList[position].title)
                intent.putExtra("time",boardList[position].time)
                intent.putExtra("content",boardList[position].content)
                // 이미지는?
                intent.putExtra("key",keyData[position])

                startActivity(intent)
            }
        })


        // 4. boardRV에 adapter 적용
        boardRv.adapter = adapter

        boardRv.layoutManager = LinearLayoutManager(requireContext())



        return view
    }

    // board에 있는 데이터를 모두 가져오는 함수
    fun getBoardData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // 리스트가 중복되어 쌓이는걸 방지하는 로직
                boardList.clear()
                // Firebase에서 snapshot으로 데이터를 받아온 경우
                // 게시물의 uid
                //      - BoardVO
                for(model in snapshot.children){
                    val item = model.getValue(BoardVO::class.java)
                    if (item != null) {
                        boardList.add(item)
                    }
                    // 이미지를 얻기위한 key값 담기
                    keyData.add(model.key.toString())

                }
                // 역순으로 배치하는 방법
                keyData.reverse()
                boardList.reverse()


                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                // 오류가 발생했을 경우 실행되는 함수
            }

        }
        // board에 있는 모든 data가 snapshot으로 들어간다
        FBdatabase.getBoardRef().addValueEventListener(postListener)
    }
}