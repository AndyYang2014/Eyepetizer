package com.andyyang.common.base.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *Created by liuyang on 2019/3/1.
 */
abstract class BindFragment<B : ViewDataBinding> : BaseFragment() {

    protected lateinit var binding: B

    @get:LayoutRes
    abstract val contentLayoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    val isBindingInitialized: Boolean
        get() {
            return this::binding.isInitialized
        }
}