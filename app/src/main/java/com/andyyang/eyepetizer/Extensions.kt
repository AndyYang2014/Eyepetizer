package com.andyyang.eyepetizer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Parcelable
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andyyang.eyepetizer.utils.ImageLoader
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import java.util.*

/**
 * Created by AndyYang.
 * data: 2018/2/14.
 * mail: AndyyYang2014@126.com.
 */

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


inline fun <reified T : Activity> Context.toActivityWithSerializable(data: Serializable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}


inline fun <reified T : Activity> Context.toActivityWithParcelable(data: Parcelable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}


fun ImageView.displayUrl(url: String) {
    ImageLoader.load(context, url, this)
}
