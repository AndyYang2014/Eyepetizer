package com.andyyang.common.base.view.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *Created by liuyang on 2019/3/1.
 */
abstract class BindActivity<B: ViewDataBinding>: BaseActivity() {

    protected lateinit var binding:B

    @get:LayoutRes
    abstract val contentLayoutId :Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initView(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this,contentLayoutId)
        binding.lifecycleOwner = this
    }

}