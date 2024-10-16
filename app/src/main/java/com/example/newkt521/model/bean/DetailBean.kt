package com.example.newkt521.model.bean

data class DetailBean(
    val message: String,
    val result: Result,
    val status: String
) {
    data class Result(
        val commentNum: Int,
        val duration: String,
        val imageUrl: String,
        val movieActor: List<MovieActor>,
        val movieDirector: List<MovieDirector>,
        val movieId: Int,
        val movieType: String,
        val name: String,
        val placeOrigin: String,
        val posterList: List<String>,
        val releaseTime: Long,
        val score: Double,
        val shortFilmList: List<ShortFilm>,
        val summary: String,
        val whetherFollow: Int
    ) {
        data class MovieActor(
            val name: String,
            val photo: String,
            val role: String
        )

        data class MovieDirector(
            val name: String,
            val photo: String
        )

        data class ShortFilm(
            val imageUrl: String,
            val videoUrl: String
        )
    }
}