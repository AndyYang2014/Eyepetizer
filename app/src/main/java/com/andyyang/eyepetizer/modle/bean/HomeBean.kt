package com.andyyang.eyepetizer.modle.bean

/**
 * Created by AndyYang.
 * data: 2018/2/18.
 * mail: AndyyYang2014@126.com.
 */
data class HomeBean(var issueList: ArrayList<Issue>, val nextPageUrl: String, val nextPublishTime: Long, val newestIssueType: String, val dialog: Any)

