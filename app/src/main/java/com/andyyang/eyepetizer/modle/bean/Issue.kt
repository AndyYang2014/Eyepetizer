package com.andyyang.eyepetizer.modle.bean

/**
* Created by AndyYang.
* data: 2018/2/20.
* mail: AndyyYang2014@126.com.
*/
data class Issue(val releaseTime:Long, val type:String, val date:Long, val total:Int, val publishTime:Long, val itemList:ArrayList<Item>, var count:Int, val nextPageUrl:String)

