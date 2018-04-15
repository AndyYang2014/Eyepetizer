package com.andyyang.eyepetizer.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Category
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.presenter.CategoryDetailPresenter
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.adapter.CategoryDetailAdapter
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.C
import kotlinx.android.synthetic.main.activity_category_detail.*

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class CategoryDetailActivity : BaseActivity() {

    private lateinit var adapter: CategoryDetailAdapter
    private val categoryDetailPresenter by lazy { CategoryDetailPresenter(this) }

    override fun getActivityLayoutId() = R.layout.activity_category_detail

    override fun initActivity(savedInstanceState: Bundle?) {
        initView()
        categoryDetailPresenter.start(intent.getParcelableExtra("data"))
    }

    private fun initView() {
        adapter = CategoryDetailAdapter()

        with(rv_category_detail) {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity)
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = this@CategoryDetailActivity.adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    val position = parent.getChildPosition(view)
                    val offset = C.dip2px(10f)!!
                    if ((position == 0)) {
                        outRect.set(0, offset, 0, 0)
                    }
                }
            })
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val childCount = this@with.childCount
                        val itemCount = this@with.layoutManager.itemCount
                        val firstVisibleItem = (this@with.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (firstVisibleItem + childCount == itemCount) {
                            if (!loadingMore) {
                                loadingMore = true
                                onLoadMore()
                            }
                        }
                    }
                }
            })
        }
    }

    var loadingMore = false

    fun onLoadMore() = categoryDetailPresenter.requestMoreData()

    fun setHeader(category: Category) = category_header.setData(category)

    fun setListData(itemList: ArrayList<Item>) {
        loadingMore = false
        adapter.addData(itemList)
    }

    fun onError() = showToast("网络错误")

}