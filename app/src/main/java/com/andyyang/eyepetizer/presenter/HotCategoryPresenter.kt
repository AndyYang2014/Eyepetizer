package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.modle.HotModel
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.ui.fragment.HotDetailFragment

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class HotCategoryPresenter(view: HotDetailFragment) : BasePresenter<HotDetailFragment>(view) {
    fun requestData(url: String) {
        hotModel.loadListData(url)
                .subscribe({
                    view.setListData(it.itemList)
                }, {
                    it.printStackTrace()
                    view.onError()
                })
    }

    private val hotModel: HotModel by lazy {
        HotModel()
    }

}