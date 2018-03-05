package com.andyyang.eyepetizer.modle

import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.modle.bean.HomeBean
import com.andyyang.eyepetizer.net.NetWork
import io.reactivex.Flowable

/**
 * Created by AndyYang.
 * data: 2018/2/19.
 * mail: AndyyYang2014@126.com.
 */
class HomeModel {

    fun loadFirstData(): Flowable<HomeBean> {
        return NetWork.api.getFirstHomeData(System.currentTimeMillis()).io_main()
    }

    fun loadMoreData(url: String): Flowable<HomeBean> {
        return NetWork.api.getMoreHomeData(url).io_main()
    }
}