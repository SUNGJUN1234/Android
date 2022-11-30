package com.example.ex20221130

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(val context: Context , val layout : Int , val data: ArrayList<PhoneVO>) : BaseAdapter() {
    // 코틀린에서 클래스를 구성하는 것은
    // 프로퍼티 : 필드
    // 멤버 : 메서드

    // Activity의 힘(context)을 빌려서 Inflate를 할 수 있는 Inflater를 가져오자
    var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    // getSystemService는 하드웨어(핸드폰)에 담겨있는 센서들이나,
    // Inflater를 추출할 수 있는 메서드
    // 많은 센서들이 담겨있고 각각의 리턴값을 설정해주기 힘듦,
    // 그래서 Any타입으로 리턴하고 있는데 내가 Inflater를 빼면 Inflater로 형변환 해줘야한다
    // ex) as LayoutInflater

    override fun getCount(): Int {
        // ListView의 항목의 개수
        return data.size
    }

    override fun getItem(p0: Int): Any {
        // p0 = position
        // position에 위치한 데이터를 반환
        return data[p0]
        // return data.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // position번째 id값
        return p0.toLong()
    }

    // ******매우 중요 (getView)
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // 데이터와 템플릿을 합친 뷰를 return 해주기
        // setContentView가 없기에 findViewById를 사용할 수 없다
        // findViewByID , setContentView : Activity만 할 수 있는 일
        // 여기는 Class이기에 뷰를 찾아오는 메서드 사용이 불가능
        // Activity의 힘을 빌릴 수 있다  --->  inflate
        // Inflate : 코드로 있는 xml 파일을 눈에 보이는 뷰로 바꿔주는 작업
        // inflate를 하기위해선 inflater를 가져와야 한다
        // MainActivity의 정보를 가져오고있는 context의 힘을 빌려 inflater를 추출하자 (15번째 줄)

        // p1은 value-parameter(val)라서 그대로 사용할 수 없으니 view에 넣어서 사용해주자
        var view = p1

        // p0 : 항목의 번호
        // p1 : 이전에 만들어진 View(xml을 눈에 보이는 형태로 바꾼 것)(첫 번째는 null)
        // p2 : 모든 View(항목)를 가지고 있는 ListView

        // 코드로 존재하는 layout을 눈에 보이는 View객체로 바꿔주자
        if(view == null){
            view = inflater?.inflate(layout, p2, false)
        }
        // layout, 누가 이 템플릿을 포함할건지?, false

        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvTel = view?.findViewById<TextView>(R.id.tvTel)
        val img = view?.findViewById<ImageView>(R.id.img)
        val btnCall = view?.findViewById<Button>(R.id.btnCall)

        // ArrayList --> data --> (id, name, tel)
        tvName?.text = data[p0].name
        tvTel?.text = data[p0].tel
        img?.setImageResource(data[p0].imgId)
        btnCall?.setOnClickListener {
            // 전화번호를 가져와서 ACTION_DIAL이 실행되게 만들자
            var tel = Uri.parse("tel:${tvTel?.text.toString()}")
            val intent = Intent(Intent.ACTION_DIAL, tel)
            // Activity만 들어갈 수 있는 stack구조(task)만으로는 startActivity를 할 수 없으니
            // Class도 들어갈 수 있는 stack구조(task)을 만들어주자 (multiTasking)

            // 새로운 Task(Stack통)을 만들어서 실행
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // ACtivity의 힘을 빌려서 Start할 예정
            // ACtivity의 힘 : context가 가지고 있음
            context.startActivity(intent)
        }

        // inflate가 된 view를 리턴 ( 데이터 + 템플릿 )
        return view!!
    }


}