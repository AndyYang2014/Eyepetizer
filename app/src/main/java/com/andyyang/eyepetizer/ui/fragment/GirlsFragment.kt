package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.GirlsList
import com.andyyang.eyepetizer.presenter.GirlsPresenter
import com.andyyang.eyepetizer.ui.activity.BigImageActivity
import com.andyyang.eyepetizer.ui.adapter.GirlsAdapter
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.andyyang.eyepetizer.utils.C
import com.jcodecraeer.xrecyclerview.XRecyclerView

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */
class GirlsFragment : BaseFragment() {

    private val pageCount = 20
    private var pageNumber = 1
    lateinit var girlsAdapter: GirlsAdapter
    val presenter by lazy { GirlsPresenter(this) }

    lateinit var recyclerView: XRecyclerView

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        recyclerView = rootView.findViewById(R.id.list_content)
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        girlsAdapter = GirlsAdapter()
        recyclerView.adapter = girlsAdapter
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        pageNumber = 1
        recyclerView.setPullRefreshEnabled(false)
        presenter.requestData(pageCount, pageNumber, true)
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                loadmore()
            }

            override fun onRefresh() {
                refresh()
            }
        })
        girlsAdapter.setOnItemClickListener(object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(v: View, position: Int) {

            }
        })
    }

    private fun loadmore() {
        recyclerView.setLoadingMoreEnabled(false)
        pageNumber++
        presenter.requestData(pageCount, pageNumber, false)

    }

    private fun refresh() {
        pageNumber = 1
        recyclerView.setPullRefreshEnabled(false)
        presenter.requestData(pageCount, pageNumber, true)
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.layout_list
    }

    fun showGirls(result: List<GirlsList.Result>, isInit: Boolean) {
        C.mainHandler.post {
            girlsAdapter.upData(result, isInit)
            if (isInit) {
                recyclerView.refreshComplete()
                recyclerView.setPullRefreshEnabled(true)
            } else {
                recyclerView.loadMoreComplete()
                recyclerView.setLoadingMoreEnabled(true)
            }
        }
    }

}
