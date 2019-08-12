package com.andyyang.network.entity

/**
* Created by AndyYang.
* data: 2018/2/25.
* mail: AndyyYang2014@126.com.
*/
data class HotCategory(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)
    data class Tab(val id: Long, val name: String, val apiUrl: String)
}