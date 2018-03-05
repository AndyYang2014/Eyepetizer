package com.andyyang.eyepetizer.utils.glide

import android.content.Context
import com.andyyang.eyepetizer.App


/**
 * Created by AndyYang
 * date:2018/2/18.
 * mail:andyyang2014@126.com
 */

class QiNiuUtils {

    private val url: StringBuffer? = null

    /**
     *
     * @param format @{Format.Jpg,Format.GIF,Format.PNG,Format.WEBP}
     * @return
     */
    fun format(format: String): QiNiuUtils? {
        url!!.append("/interlace/")
        url.append(format)
        return qiNiu
    }

    //图片格式类
    interface Format {
        companion object {
            val JPG = "jpg"
            val GIF = "gif"
            val PNG = "pne"
            val WEBP = "webp"
        }
    }


    /**
     * 图片质量
     * @param quality 默认75 ，取值范围 0-100
     * @param isSure 是否强制获得指定质量的图片
     * @return
     */
    fun quality(quality: Int, isSure: Boolean): QiNiuUtils? {
        url!!.append("/q/")
        if (quality < 0) {
            url.append(0)
        } else if (quality > 100) {
            url.append(100)
        } else {
            url.append(quality)
        }
        if (isSure) {
            url.append("!")
        }
        return qiNiu
    }

    //渐进式显示
    fun interlace(enable: Boolean): QiNiuUtils? {
        url!!.append("/interlace/")
        if (enable) {
            url.append(1)
        } else {
            url.append(0)
        }
        return qiNiu
    }


    //获得处理后的图片路径
    fun commit(): String {
        return qiNiu!!.url!!.toString()
    }

    companion object {
        private val qiNiu: QiNiuUtils? = null

        fun setUrl(url: String, width: Int): String {
            var url = url
            url += "?imageView2/0/w/" + dip2px(App.context, width.toFloat())
            return url
        }

        private fun dip2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }

}
