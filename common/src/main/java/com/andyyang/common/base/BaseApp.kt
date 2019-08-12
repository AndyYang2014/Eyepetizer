package com.andyyang.common.base

import android.app.Application
import kotlin.properties.Delegates

/**
 *Created by liuyang on 2019/7/17.
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        var INSTANCE by Delegates.notNull<BaseApp>()
    }

}