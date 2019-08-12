package com.andyyang.common.utils

import java.util.*

/**
 *Created by liuyang on 2019/7/17.
 */


fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute' 0$second''"
        } else {
            "0$minute' $second''"
        }
    } else {
        if (second <= 9) {
            "$minute' 0$second''"
        } else {
            "$minute' $second''"
        }
    }
}

fun timeFormat(time: Long): String {
    val date = Date(time)
    val timeCalendar = Calendar.getInstance()
    timeCalendar.time = date


    val today = Calendar.getInstance()
    val todayDate = Date(System.currentTimeMillis())
    today.time = todayDate

    if (timeCalendar.get(Calendar.YEAR) === today.get(Calendar.YEAR)) {
        val diffDay = timeCalendar.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR)

        if (diffDay == 0) {
            //是今天
            val hours = timeCalendar.get(Calendar.HOUR_OF_DAY)
            val minues = timeCalendar.get(Calendar.MINUTE)
            return "${if (hours < 10) "0" + hours else hours}:${if (minues < 10) "0" + minues else minues}"
        }
    }
    val year = timeCalendar.get(Calendar.YEAR)
    val month = timeCalendar.get(Calendar.MONTH)
    val day = timeCalendar.get(Calendar.DAY_OF_MONTH)
    return "$year/${if (month < 10) "0" + month else month}/${if (day < 10) "0" + day else day}"
}

/**
 * 几天前  几小时前
 */
fun timePreFormat(time: Long): String {

    val now = System.currentTimeMillis()
    val pre = now - time//多久前


    val min = pre / 1000 / 60
    return when {
        min < 1 -> "刚刚"
        min < 60 -> "" + min + "分钟前"
        min < 60 * 24 -> "" + min / 60 + "小时前"
        else -> "" + min / 60 / 24 + "天前"
    }
}

fun dataFormat(total: Long): String {
    val result: String
    val speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}