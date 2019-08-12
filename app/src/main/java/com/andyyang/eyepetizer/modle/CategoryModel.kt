package com.andyyang.eyepetizer.modle

import com.andyyang.eyepetizer.modle.bean.Category
import io.reactivex.Flowable

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class CategoryModel {

    fun loadData(): Flowable<ArrayList<Category>> {
        return NetWork.api.getCategory().io_main()
    }
}