package com.andyyang.eyepetizer.ui.base

import com.andyyang.eyepetizer.interfaces.LifeCycle
import com.andyyang.eyepetizer.interfaces.OnLifeCycleListener
import com.andyyang.eyepetizer.interfaces.RxJavaImpl
import com.andyyang.eyepetizer.utils.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by AndyYang.
 * data: 2018/2/13.
 * mail: AndyyYang2014@126.com.
 */
open class BasePresenter<T : LifeCycle>(protected var view: T):RxJavaImpl {
    private val disposables = CompositeDisposable()

    override fun dispose(disposable: Disposable) {
        disposables.remove(disposable)
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    init {
        view.setOnLifeCycleListener(object : OnLifeCycleListener {
            override fun onStart() {
                this@BasePresenter.onStart()
            }

            override fun onResume() {
                this@BasePresenter.onResume()
            }

            override fun onPause() {
                this@BasePresenter.onPause()
            }

            override fun onStop() {
                this@BasePresenter.onStop()
            }

            override fun onDestroy() {
                this@BasePresenter.onDestory()
            }
        })
    }


    open fun onStart() {
        Logger.d(this.javaClass.simpleName + "onStart")
    }

    open fun onResume() {
        Logger.d(this.javaClass.simpleName + "onResume")
    }

    open fun onPause() {
        Logger.d(this.javaClass.simpleName + "onPause")
    }

    open fun onStop() {
        disposables.clear()
        Logger.d(this.javaClass.simpleName + "onStop")
    }

    open fun onDestory() {
        Logger.d(this.javaClass.simpleName + "onDestory")
    }

}
