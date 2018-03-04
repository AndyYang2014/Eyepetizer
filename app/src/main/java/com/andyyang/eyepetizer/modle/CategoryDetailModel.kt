package com.andyyang.eyepetizer.modle

import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.modle.bean.Issue
import com.andyyang.eyepetizer.net.NetWork
import io.reactivex.Flowable

/**
* Created by AndyYang.
* data: 2018/2/24.
* mail: AndyyYang2014@126.com.
*/
class CategoryDetailModel {

    fun loadData(id: Long): Flowable<Issue> {
        return NetWork.api.getCategoryItemList(id).io_main()
    }

    fun loadMoreData(url: String): Flowable<Issue> {
        return NetWork.api.getIssue(url).io_main()
    }
}