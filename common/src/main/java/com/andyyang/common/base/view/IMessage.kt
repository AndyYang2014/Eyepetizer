package com.andyyang.common.base.view

/**
 *Created by liuyang on 2019/7/26.
 */
interface IMessage {

    fun showLoading()

    fun hideLoading()

    fun showToast(message:String)
}