package com.andyyang.eyepetizer.ui.view.detail

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.andyyang.eyepetizer.R
import kotlinx.android.synthetic.main.layout_list_end.view.*

/**
 * Created by AndyYang.
 * data: 2018/2/28.
 * mail: AndyyYang2014@126.com.
 */
class ListEndView : FrameLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.layout_list_end, this)
        tv_text_end?.typeface = Typeface.createFromAsset(context.assets, "fonts/Lobster-1.4.otf")
    }

    fun setShow(show: Boolean) {
        tv_text_end.visibility = if (show) View.VISIBLE else View.GONE
    }

}