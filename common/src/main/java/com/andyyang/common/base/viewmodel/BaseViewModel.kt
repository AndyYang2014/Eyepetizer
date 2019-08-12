package com.andyyang.common.base.viewmodel

import com.andyyang.common.base.view.IMessage
import io.reactivex.Single


open class BaseViewModel : AutoDisposeViewModel(){

    private var mImessage: IMessage? = null

    fun setImessage(iMessage: IMessage){
        this.mImessage = iMessage
    }

    protected fun showLoading(){
        mImessage?.showLoading()
    }

    protected fun hideLoading(){
        mImessage?.hideLoading()
    }

    protected fun toast(msg:String){
        mImessage?.showToast(msg)
    }

    protected fun <T> Single<T>.withLoading(): Single<T> {
        return this
            .doOnSubscribe {
                showLoading()
            }.doFinally {
                hideLoading()
            }
    }
}