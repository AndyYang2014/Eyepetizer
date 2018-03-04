package com.andyyang.eyepetizer.interfaces

import io.reactivex.disposables.Disposable

/**
 * Created by AndyYang.
 * data: 2018/2/17.
 * mail: AndyyYang2014@126.com.
 */
interface RxJavaImpl {
    fun dispose(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}