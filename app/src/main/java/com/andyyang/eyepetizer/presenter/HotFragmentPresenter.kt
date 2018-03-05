package com.andyyang.eyepetizer.presenter

import android.util.Log
import com.andyyang.eyepetizer.modle.HotModel
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.ui.fragment.HotFragment

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */
class HotFragmentPresenter(view: HotFragment) : BasePresenter<HotFragment>(view) {
    fun requestHotCategory() {
        hotModel.loadCategoryData("http://baobab.kaiyanapp.com/api/v4/rankList")
                .subscribe({
                    Log.i("HotFragmentPresenter", "requestHotCategory-->$it")
                    view.setTabAndFragment(it)
                }, {
                    it.printStackTrace()
                    view.onError()
                })
    }

    val hotModel: HotModel by lazy { HotModel() }
}