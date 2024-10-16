package com.example.newkt521.model.bean



data class RecommendBean(
    val message: String,
    val result: List<Result>,
    val status: String
) {data class Result(
    val address: String,
    val commentTotal: Int,
    val distance: Int,
    val followCinema: Int,
    val id: Int,
    val logo: String,
    val name: String
)
}