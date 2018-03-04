package com.andyyang.eyepetizer

import android.app.Application
import com.andyyang.eyepetizer.utils.C
import kotlin.properties.Delegates

/**
 * Created by AndyYang.
 * data: 2018/2/14.
 * mail: AndyyYang2014@126.com.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        C.init(this)
    }

    companion object {
        var context by Delegates.notNull<App>()
    }
}