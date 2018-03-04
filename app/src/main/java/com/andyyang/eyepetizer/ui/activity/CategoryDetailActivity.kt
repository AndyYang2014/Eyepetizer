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

    val adapter by lazy { CategoryDetailAdapter() }
    lateinit var categoryDetailPresenter: CategoryDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        val category = intent.getParcelableExtra<Category>("data")
        categoryDetailPresenter = CategoryDetailPresenter(this)
        initView()
        categoryDetailPresenter.start(category)
    }

    fun initView() {
        rv_category_detail.layoutManager = LinearLayoutManager(this)
        rv_category_detail.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        rv_category_detail.adapter = adapter
        rv_category_detail.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = C.dip2px(10f)!!
                if ((position == 0)) {
                    outRect.set(0, offset, 0, 0)
                }
            }
        })


        rv_category_detail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = rv_category_detail.childCount
                    val itemCount = rv_category_detail.layoutManager.getItemCount()
                    val firstVisibleItem = (rv_category_detail.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
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

    var loadingMore = false
    fun onLoadMore() {
        categoryDetailPresenter.requestMoreData()
    }

    fun setHeader(category: Category) {
        category_header.setData(category)
    }

    fun setListData(itemList: ArrayList<Item>) {
        loadingMore = false
        adapter.addData(itemList)
    }

    fun onError() {
        showToast("网络错误")
    }

}