package com.andyyang.common.base.view.activity

import android.os.Bundle
import android.widget.Toast
import com.andyyang.common.base.view.IMessage

open class BaseActivity : InjectionActivity(), IMessage {

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    protected fun showLoadingDialog(){

    }

    protected fun hideLoadingDialog(){

    }
}