package com.example.newkt521.model.bean

data class XBannerBean (
    val meassage:String,
    val result: List<Result>,
    val status:String
){
    data class Result(
        val imageUrl:String,
        val jumpUrl:String,
        val rank:Int
    )
}