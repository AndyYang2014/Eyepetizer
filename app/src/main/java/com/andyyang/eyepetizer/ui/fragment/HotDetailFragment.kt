package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.presenter.HotCategoryPresenter
import com.andyyang.eyepetizer.ui.adapter.HotCategoryAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_list.*

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class HotDetailFragment(var apiUrl: String) : BaseFragment() {

    val presenter by lazy { HotCategoryPresenter(this) }

    override fun initFragment(savedInstanceState: Bundle?) {
        with(list_content) {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@HotDetailFragment.adapter
            setLoadingMoreEnabled(false)
            setPullRefreshEnabled(false)
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        presenter.requestData(apiUrl)
    }

    override fun getFragmentLayoutId() = R.layout.layout_list

    val adapter by lazy { HotCategoryAdapter() }

    fun setListData(itemList: ArrayList<Item>) {
        adapter.addItemList(itemList)
    }

    fun onError() {
    }

}