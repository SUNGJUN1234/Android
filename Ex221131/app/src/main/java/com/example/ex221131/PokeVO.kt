package com.example.ex221131

class PokeVO(val img:Int,val name:String, val type:String) {

    var level:String

    init{
        // 생성 시 가장 먼저 호출되는 부분
        // 초기화 시킬 대상들을 넣어준다
        level = "1"
    }

}