package com.andyyang.common.utils

import android.app.ActivityManager
import android.content.ClipboardManager
import android.content.Context
import android.os.Process
import android.util.TypedValue

/**
 *Created by liuyang on 2019/7/17.
 */

fun Context.getCurrentProcessName(): String? {
    val pid = Process.myPid()
    val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return am.runningAppProcesses.firstOrNull { it.pid == pid }?.processName
}

fun Context.dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}

fun Context.getClipboardManager(): ClipboardManager =
    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

 