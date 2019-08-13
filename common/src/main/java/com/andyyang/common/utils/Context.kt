package com.andyyang.common.utils

import android.app.ActivityManager
import android.content.ClipboardManager
import android.content.Context
import android.os.Process
import android.util.TypedValue
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by liuyang on 2019/7/17.
 */

private const val STANDARD_WIDTH = 1080
private const val STANDARD_HEIGHT = 1920

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


fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun <T> Flowable<T>.io_main(): Flowable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.io_main(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Context.getDisplayMetrics() = resources.displayMetrics

fun Context.getScreenXY(): Pair<Int, Int> {
    return getDisplayMetrics().widthPixels to getDisplayMetrics().heightPixels
}

fun Context.getScreenDpi(): Int {
    return getDisplayMetrics().densityDpi
}


fun Context.getRealWidth(px: Int, parentWidth: Float): Int? {
    return (px / parentWidth * getScreenXY().first).toInt()
}

fun Context.getPaintSize(size: Int): Int? {
    return getRealHeight(size,STANDARD_HEIGHT.toFloat())
}


fun Context.getRealHeight(px: Int, parentHeight: Float): Int? {
    return (px / parentHeight * getScreenXY().second).toInt()
}
