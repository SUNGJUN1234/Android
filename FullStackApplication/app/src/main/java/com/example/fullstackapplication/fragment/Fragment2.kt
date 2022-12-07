package com.example.fullstackapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.ListActivity

class Fragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_2,container,false)

        val imgAll = view.findViewById<ImageView>(R.id.imgAll)
        val imgCook = view.findViewById<ImageView>(R.id.imgCook)
        val imgLife = view.findViewById<ImageView>(R.id.imgLife)

        imgAll.setOnClickListener{

            // 메인 글자 옮겨주기
            val spf = requireActivity().getSharedPreferences(
                "mySpf",
                Context.MODE_PRIVATE
            )
            var editor = spf.edit()
            editor.putString("category",imgAll.tag.toString())
            editor.commit()

            val intent = Intent(context, ListActivity::class.java)
            // intent로 보내기
            intent.putExtra("category",imgAll.tag.toString())

            startActivity(intent)
        }

        imgCook.setOnClickListener{
            val spf = requireActivity().getSharedPreferences(
                "mySpf",
                Context.MODE_PRIVATE
            )
            var editor = spf.edit()
            editor.putString("category",imgCook.tag.toString())
            editor.commit()
            val intent = Intent(context,ListActivity::class.java)
            startActivity(intent)

        }

        imgLife.setOnClickListener{
            val spf = requireActivity().getSharedPreferences(
                "mySpf",
                Context.MODE_PRIVATE
            )
            var editor = spf.edit()
            editor.putString("category",imgLife.tag.toString())
            editor.commit()
            val intent = Intent(context,ListActivity::class.java)
            startActivity(intent)

        }

        return view
    }

}