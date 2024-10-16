package com.example.newkt521.model.bean

data class ComingSoonBean(
    val message:String,
    val result:List<Result>,
    val status:String
) {
    data class Result(
    val imageUrl:String,
    val movieId:Int,
    val name:String,
    val releaseTime:Long,
    val wantSeeNum:Int,
    val whetherReserve:Int
    )
}