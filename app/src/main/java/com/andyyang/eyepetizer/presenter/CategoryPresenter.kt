package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.modle.CategoryModel
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.ui.fragment.CategoryFragment

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class CategoryPresenter(view: CategoryFragment) : BasePresenter<CategoryFragment>(view) {

    val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    fun requestData() {
        categoryModel.loadData()
                .subscribe({ view.showCategory(it) }, {
                    it.printStackTrace()
                })
    }

}