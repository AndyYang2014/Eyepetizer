package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.modle.HomeModel
import com.andyyang.eyepetizer.modle.bean.HomeBean
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.ui.fragment.HomeFragment

/**
 * Created by AndyYang.
 * data: 2018/2/21.
 * mail: AndyyYang2014@126.com.
 */
class HomePresenter(view: HomeFragment) : BasePresenter<HomeFragment>(view) {
    var nextPageUrl: String? = null
    val homeModel: HomeModel by lazy { HomeModel() }
    var bannerHomeBean: HomeBean? = null

    fun requestFirstData() {
        homeModel.loadFirstData()
                .flatMap({
                    bannerHomeBean = it
                    homeModel.loadMoreData(it.nextPageUrl)
                })
                .subscribe({
                    nextPageUrl = it.nextPageUrl
                    bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size//这里记录轮播图的长度，在adapter中用

                    val newItemList = it.issueList[0].itemList
                    newItemList.filter { it.type == "banner2" }.forEach { newItemList.remove(it) }

                    bannerHomeBean?.issueList!![0].itemList.addAll(newItemList)
                    view.setFirstData(bannerHomeBean!!)
                }, {
                    it.printStackTrace()
                    view.onError()
                })
    }

    fun requestMoreData() {
        nextPageUrl?.let {
            homeModel.loadMoreData(it)
                    .subscribe({
                        val newItemList = it.issueList[0].itemList
                        newItemList.filter { it.type == "banner2" }.forEach { newItemList.remove(it) }
                        view.setMoreData(newItemList)
                        nextPageUrl = it.nextPageUrl
                    })
        }
    }

}