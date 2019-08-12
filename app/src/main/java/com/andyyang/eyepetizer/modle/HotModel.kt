package com.andyyang.eyepetizer.modle

import com.andyyang.eyepetizer.modle.bean.HotCategory
import com.andyyang.eyepetizer.modle.bean.Issue
import io.reactivex.Flowable

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class HotModel {

    fun loadListData(url: String): Flowable<Issue> {
        return NetWork.api.getIssue(url).io_main()
    }

    fun loadCategoryData(url: String): Flowable<HotCategory> {
        return NetWork.api.getHotCategory(url).io_main()
    }
}