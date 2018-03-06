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
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by AndyYang.
 * data: 2018/2/16.
 * mail: AndyyYang2014@126.com.
 */
class CategoryFragment : BaseFragment() {
    lateinit var recyclerView: XRecyclerView
    lateinit var adapter: CategoryAdapter
    val categoryPresenter by lazy { CategoryPresenter(this) }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        recyclerView = rootView.findViewById(R.id.list_content)

        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (gridLayoutManager.itemCount - 1 == position) {
                    return 2
                }
                return 1
            }

        }
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        adapter = CategoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.setLoadingMoreEnabled(false)
        recyclerView.setPullRefreshEnabled(false)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = C.dip2px(2f)!!

                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }

        })
        adapter.onClick = { activity.toActivityWithParcelable<CategoryDetailActivity>(it) }
        categoryPresenter.requestData()

    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.layout_list
    }

    fun showCategory(categorys: ArrayList<Category>) {
        adapter.setData(categorys)
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
        activity.toolbar.setBackgroundColor(0xddffffff.toInt())
        activity.iv_search.setImageResource(R.drawable.ic_action_search)
        activity.tv_bar_title.text = "分类"
        return true
    }


}