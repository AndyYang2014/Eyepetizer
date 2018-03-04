package com.andyyang.eyepetizer.ui.view.home.banner

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by AndyYang.
 * data: 2018/2/25.
 * mail: AndyyYang2014@126.com.
 */
class HomeBannerTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width: Int = page.width
        page.scrollX = (position * width).toInt() / 4 * 3
    }
}
