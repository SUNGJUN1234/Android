package com.example.ex221205

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ContactActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")
        val color = database.getReference("color")

        val clHome = findViewById<ConstraintLayout>(R.id.clHome)

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

        myRef.setValue("Hello, World!")


    }
}