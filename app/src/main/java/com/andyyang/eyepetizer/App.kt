package com.andyyang.eyepetizer

import com.andyyang.common.base.BaseApp
import com.andyyang.eyepetizer.utils.C

/**
 * Created by AndyYang.
 * data: 2018/2/14.
 * mail: AndyyYang2014@126.com.
 */
class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        C.init(this)
    }

}