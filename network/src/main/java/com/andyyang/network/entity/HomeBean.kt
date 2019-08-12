package com.andyyang.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AndyYang.
 * data: 2018/2/18.
 * mail: AndyyYang2014@126.com.
 */
@Parcelize
data class HomeBean(
    var issueList: ArrayList<Issue>,
    val nextPageUrl: String,
    val nextPublishTime: Long,
    val newestIssueType: String,
    val dialog: String?
) : Parcelable

@Parcelize
data class Issue(
    val releaseTime: Long,
    val type: String,
    val date: Long,
    val total: Int,
    val publishTime: Long,
    val itemList: ArrayList<Item>,
    var count: Int,
    val nextPageUrl: String
) : Parcelable

