package com.example.newkt521.model.bean

data class RegionListBean(
    val message: String,
    val result: List<Result>,
    val status: String
) {
    data class Result(
        val regionId: Int,
        val regionName: String
    )
}