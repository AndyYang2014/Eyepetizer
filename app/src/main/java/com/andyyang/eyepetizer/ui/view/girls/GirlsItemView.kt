package com.andyyang.eyepetizer.ui.view.girls

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.displayUrl
import com.andyyang.eyepetizer.modle.bean.GankInfo
import kotlinx.android.synthetic.main.view_item_girls.view.*

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */
class GirlsItemView : FrameLayout {

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

    fun bindData(result: GankInfo.Result) {
        girl_face.displayUrl(result.url)
        girl_date.text = result.publishedAt.substring(0, 10)
    }


}