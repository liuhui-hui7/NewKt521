package com.example.newkt521.model.bean

data class CinemaByRegionBean(
    val message: String,
    val result: List<Result>,
    val status: String
) {
    data class Result(
        val id: Int,
        val name: String
    )
}