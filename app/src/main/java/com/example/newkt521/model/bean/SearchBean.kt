package com.example.newkt521.model.bean

data class SearchBean(
    val message:String,
    val result: List<Result>,
    val status : String,

    ){
    data class Result(
        val director:String,
        val imageUrl:String,
        val movieId:Int,
        val name: String,
        val score: Double,
        val starring: String
    )
}