package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.modle.CategoryDetailModel
import com.andyyang.eyepetizer.modle.bean.Category
import com.andyyang.eyepetizer.ui.activity.CategoryDetailActivity
import com.andyyang.eyepetizer.ui.base.BasePresenter

/**
 * Created by AndyYang.
 * data: 2018/2/26.
 * mail: AndyyYang2014@126.com.
 */
class CategoryDetailPresenter(view: CategoryDetailActivity) : BasePresenter<CategoryDetailActivity>(view) {

    var nextPageUrl = ""

    fun requestMoreData() {
        categoryDetailModel.loadMoreData(nextPageUrl)
                .subscribe({
                    nextPageUrl = it.nextPageUrl
                    view.setListData(it.itemList)
                }, {
                    it.printStackTrace()
                    view.onError()
                })
    }

    fun start(category: Category) {
        view.setHeader(category)
        categoryDetailModel.loadData(category.id)
                .subscribe({
                    nextPageUrl = it.nextPageUrl
                    view.setListData(it.itemList)
                }, {
                    it.printStackTrace()
                    view.onError()
                })
    }


    val categoryDetailModel: CategoryDetailModel by lazy {
        CategoryDetailModel()
    }

}