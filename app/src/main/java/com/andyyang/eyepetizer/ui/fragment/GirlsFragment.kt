package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.GankInfo
import com.andyyang.eyepetizer.presenter.GirlsPresenter
import com.andyyang.eyepetizer.ui.adapter.GirlsAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_list.*
import org.jetbrains.anko.support.v4.onUiThread

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

    override fun initFragment(savedInstanceState: Bundle?) {
        pageNumber = 1
        girlsAdapter = GirlsAdapter()
        with(list_content) {
            layoutManager = GridLayoutManager(mActivity, 2)
            adapter = girlsAdapter
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPullRefreshEnabled(false)
            setLoadingListener(object : XRecyclerView.LoadingListener {
                override fun onLoadMore() {
                    loadmore()
                }

                override fun onRefresh() {
                    this@GirlsFragment.refresh()
                }
            })
        }

        presenter.requestData(pageCount, pageNumber, true)
    }

    private fun loadmore() {
        list_content.setLoadingMoreEnabled(false)
        pageNumber++
        presenter.requestData(pageCount, pageNumber, false)
    }

    private fun refresh() {
        pageNumber = 1
        list_content.setPullRefreshEnabled(false)
        presenter.requestData(pageCount, pageNumber, true)
    }

    override fun getFragmentLayoutId() = R.layout.layout_list

    fun showGirls(result: List<GankInfo.Result>, isInit: Boolean) {
        onUiThread {
            girlsAdapter.upData(result, isInit)
            if (isInit) {
                list_content.refreshComplete()
                list_content.setPullRefreshEnabled(true)
            } else {
                list_content.loadMoreComplete()
                list_content.setLoadingMoreEnabled(true)
            }
        }
    }

    var isFirst = true


    override fun onResume() {
        super.onResume()
        if (isFirst) {
            setupToolbar()
            isFirst = false
        }
    }

    override fun setupToolbar(): Boolean {
//        if (super.setupToolbar()) {
//            return true
//        }
//        super.setupToolbar()
        mActivity.toolbar.setBackgroundColor(0xddffffff.toInt())
        mActivity.iv_search.setImageBitmap(null)
        mActivity.tv_bar_title.text = "妹子"
        return true
    }

}
