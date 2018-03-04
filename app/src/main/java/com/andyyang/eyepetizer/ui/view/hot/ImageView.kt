package com.andyyang.eyepetizer.ui.view.hot

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class ImageView : AppCompatImageView {


    constructor(context: Context) : super(context) {
        isDrawingCacheEnabled = true
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        isDrawingCacheEnabled = true
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        isDrawingCacheEnabled = true
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isDrawingCacheEnabled = false
        setImageDrawable(null)
    }
}