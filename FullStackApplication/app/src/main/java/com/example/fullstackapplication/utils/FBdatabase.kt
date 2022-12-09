package com.example.fullstackapplication.utils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBdatabase {

    // realtime DB사용은 이 클래스를 통해서 진행하자
    companion object{
        val database = Firebase.database

        // 앞으로 호출하고 싶다면
        // FBdatabase.getBoardRef()
        fun getBoardRef() : DatabaseReference{
            return database.getReference("board")
        }
        // FBdatabase.getContentRef()
        fun getContentRef() : DatabaseReference{
            return database.getReference("content")
        }
        // FBdatabase.getBookmarkRef()
        fun getBookmarkRef() : DatabaseReference{
            return database.getReference("bookmarklist")
        }
    }
}