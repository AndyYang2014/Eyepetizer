package com.andyyang.eyepetizer.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Category
import com.andyyang.eyepetizer.presenter.CategoryPresenter
import com.andyyang.eyepetizer.toActivityWithParcelable
import com.andyyang.eyepetizer.ui.activity.CategoryDetailActivity
import com.andyyang.eyepetizer.ui.adapter.CategoryAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.andyyang.eyepetizer.utils.C
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_list.*
import java.util.*

/**
 * Created by AndyYang.
 * data: 2018/2/16.
 * mail: AndyyYang2014@126.com.
 */
class CategoryFragment : BaseFragment() {

    lateinit var adapter: CategoryAdapter
    private val categoryPresenter by lazy { CategoryPresenter(this) }

    override fun initFragment(savedInstanceState: Bundle?) {
        val gridLayoutManager = GridLayoutManager(mActivity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (gridLayoutManager.itemCount - 1 == position) {
                    return 2
                }
                return 1
            }
        }
        adapter = CategoryAdapter()
        with(list_content) {
            layoutManager = gridLayoutManager
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = this@CategoryFragment.adapter
            setLoadingMoreEnabled(false)
            setPullRefreshEnabled(false)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    val position = parent.getChildPosition(view)
                    val offset = C.dip2px(2f)!!

                    outRect.set(if (position % 2 == 0) 0 else offset, offset,
                            if (position % 2 == 0) offset else 0, offset)
                }
            })

        }
        adapter.onClick = { mActivity.toActivityWithParcelable<CategoryDetailActivity>(it) }
        categoryPresenter.requestData()
    }

    override fun getFragmentLayoutId() = R.layout.layout_list

    fun showCategory(categorys: ArrayList<Category>) = adapter.setData(categorys)

    private var isFirst = true

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
        mActivity.iv_search.setImageResource(R.drawable.ic_action_search)
        mActivity.tv_bar_title.text = "分类"
        return true
    }


}