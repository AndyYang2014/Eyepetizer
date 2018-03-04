package com.andyyang.eyepetizer.ui.view.hot

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.durationFormat
import com.andyyang.eyepetizer.modle.bean.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_hot_item.view.*

/**
 * Created by AndyYang.
 * data: 2018/2/22.
 * mail: AndyyYang2014@126.com.
 */
class HotItem : FrameLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_hot_item, this)


    }

    fun setData(item: Item) {
        jst_name.text = item.data?.title
        jst_tag.text = "#" + item.data?.category + " / " + durationFormat(item.data?.duration)
        Glide.with(context).load(item.data?.cover?.feed).centerCrop().into(this.hot_image)

    }
}