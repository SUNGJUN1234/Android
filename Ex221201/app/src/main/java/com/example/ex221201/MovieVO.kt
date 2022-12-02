package com.example.ex221201

// class 앞의 data 는 약속 (data를 붙이면 getter,setter를 자동으로 만들어주니 VO에는 붙여주는게 좋다)
data class MovieVO(var rank:String, var rankOldAndNew:String,var MovieNm:String,var openDt:String, var audiAcc:String) {
}