package com.andyyang.eyepetizer.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import com.andyyang.eyepetizer.App

/**
 * Created by AndyYang.
 * data: 2018/2/14.
 * mail: AndyyYang2014@126.com.
 */
object C {
    lateinit var threadPool: UThread.TreadPool
    lateinit var mainHandler: Handler
    lateinit var app: App
        private set

    private var displayMetrics: DisplayMetrics? = null
    private var screenWidth: Int? = null
    private var screenHeight: Int? = null
    private var screenDpi: Int? = null

    fun init(application: App) {
        app = application
        threadPool = UThread.Pool()
        mainHandler = Handler(Looper.getMainLooper())

        displayMetrics = application.resources.displayMetrics
        screenWidth = displayMetrics?.widthPixels
        screenHeight = displayMetrics?.heightPixels
        screenDpi = displayMetrics?.densityDpi
    }


    fun getContext(): Context {
        return app.applicationContext
    }


    private val STANDARD_WIDTH = 1080
    private val STANDARD_HEIGHT = 1920

    fun getScreenWidth(): Int? {
        return screenWidth
    }

    fun getScreenHeight(): Int? {
        return screenHeight
    }

    fun getPaintSize(size: Int): Int? {
        return getRealHeight(size)
    }

    fun getRealWidth(px: Int): Int? {
        return getRealWidth(px, STANDARD_WIDTH.toFloat())
    }

    fun getRealWidth(px: Int, parentWidth: Float): Int? {
        return (px / parentWidth * getScreenWidth()!!).toInt()
    }

    fun getRealHeight(px: Int): Int? {
        return getRealHeight(px, STANDARD_HEIGHT.toFloat())
    }


    fun getRealHeight(px: Int, parentHeight: Float): Int? {
        return (px / parentHeight * getScreenHeight()!!).toInt()
    }

    fun dip2px(dipValue: Float): Int? {
        val scale = displayMetrics?.density
        return (dipValue * scale!! + 0.5f).toInt()
    }

    interface Url {
        companion object {

            const val base_url: String = "http://baobab.kaiyanapp.com/api/"

            const val RANDOM = "http://gank.io/api/random/data/福利"

            const val GIRL = "http://gank.io/api/data/福利"

        }
    }
}
