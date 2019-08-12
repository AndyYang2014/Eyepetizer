package com.andyyang.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
@Parcelize
data class Category(
    val id: Long,
    val name: String,
    val description: String,
    val bgPicture: String,
    val bgColor: String,
    val headerImage: String
) : Parcelable