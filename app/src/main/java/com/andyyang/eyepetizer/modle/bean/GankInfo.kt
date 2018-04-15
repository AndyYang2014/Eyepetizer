package com.andyyang.eyepetizer.modle.bean

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

data class GankInfo(val error: Boolean, val results: List<Result>) {

    data class Result(
            val _id: String,
            val createdAt: String,
            val desc: String,
            val publishedAt: String,
            val source: String,
            val type: String,
            val url: String,
            val used: Boolean,
            val who: String
    )
}
