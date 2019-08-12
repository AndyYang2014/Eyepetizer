package com.andyyang.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

@Parcelize
data class GankInfo(
    val error: Boolean,
    val results: List<Result>
) : Parcelable

@Parcelize
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
) : Parcelable
