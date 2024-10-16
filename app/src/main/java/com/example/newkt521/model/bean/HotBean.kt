package com.example.newkt521.model.bean


data class HotBean(
    val message: String,
    val result: List<Result>,
    val status:String
) {
    data class Result(
        val director: String,
        val horizontalImage: String,
        val imageUrl: String,
        val movieId: Int,
        val name: String,
        val score: Double,
        val starring: String

    )
}