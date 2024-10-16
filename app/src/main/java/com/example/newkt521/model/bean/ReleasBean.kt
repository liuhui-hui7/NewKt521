package com.example.newkt521.model.bean

import javax.net.ssl.SSLEngineResult.Status

class ReleasBean (
    val message:String,
    val result:List<Result>,
    val status: String
){
    class Result(
        val director:String,
        val imageUrl:String,
        val movieId:Int,
        val name:String,
        val score:Double,
        val starring:String
    )
}