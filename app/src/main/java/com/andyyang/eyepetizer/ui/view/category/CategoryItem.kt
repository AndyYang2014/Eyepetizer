package com.andyyang.eyepetizer.ui.view.category

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Category
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_category_item.view.*

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class CategoryItem : FrameLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.layout_category_item, this)
    }

    fun setData(category: Category) {
        tv_name.text = "#"+category.name
        Glide.with(context).load(category.bgPicture).centerCrop().into(iv_category)
    }
}