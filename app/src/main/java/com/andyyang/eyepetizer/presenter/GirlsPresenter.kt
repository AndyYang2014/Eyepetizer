package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.net.NetWork
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.ui.fragment.GirlsFragment

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class GirlsPresenter(view: GirlsFragment) : BasePresenter<GirlsFragment>(view) {

    fun requestData(pageCount: Int, pageNumber: Int, isInit: Boolean) {
        NetWork.api.girls(pageCount, pageNumber).io_main()
                .subscribe({ view.showGirls(it.results, isInit) }, {
                    view.showToast(it.toString())
                })
    }

}