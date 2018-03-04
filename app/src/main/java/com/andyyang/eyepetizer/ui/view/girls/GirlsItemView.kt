package com.andyyang.eyepetizer.ui.view.girls

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.GirlsList
import com.andyyang.eyepetizer.ui.activity.BigImageActivity
import com.andyyang.eyepetizer.utils.glide.ImageLoader
import kotlinx.android.synthetic.main.view_item_girls.view.*

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */
class GirlsItemView : FrameLayout {

    private lateinit var information: GirlsList.Result

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }


    private fun init(context: Context) {
        val view = View.inflate(context, R.layout.view_item_girls, this)
    }

    fun bindData(result: GirlsList.Result) {
        information = result
        ImageLoader.load(context, result.url, girl_face)
        girl_date.text = result.publishedAt.substring(0, 10)
    }


}