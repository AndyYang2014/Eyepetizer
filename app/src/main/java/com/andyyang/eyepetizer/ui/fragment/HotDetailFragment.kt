package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.presenter.HotCategoryPresenter
import com.andyyang.eyepetizer.ui.adapter.HotCategoryAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.jcodecraeer.xrecyclerview.XRecyclerView

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class HotDetailFragment(var apiUrl: String) : BaseFragment() {

    lateinit var recyclerView: RecyclerView
    val presenter by lazy { HotCategoryPresenter(this) }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        recyclerView = rootView.findViewById(R.id.list_content)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        presenter.requestData(apiUrl)
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.layout_list
    }


    val adapter by lazy { HotCategoryAdapter() }


    fun setListData(itemList: ArrayList<Item>) {
        adapter.addItemList(itemList)
    }

    fun onError() {
    }

}