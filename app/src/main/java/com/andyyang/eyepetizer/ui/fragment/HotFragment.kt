package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.HotCategory
import com.andyyang.eyepetizer.presenter.HotFragmentPresenter
import com.andyyang.eyepetizer.ui.adapter.HotViewPagerAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.andyyang.eyepetizer.utils.ViewUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class HotFragment : BaseFragment() {

    val presenter by lazy { HotFragmentPresenter(this) }

    override fun initFragment(savedInstanceState: Bundle?) = presenter.requestHotCategory()

    override fun getFragmentLayoutId() = R.layout.fragment_hot

    fun setTabAndFragment(hotCategory: HotCategory) {
        val titleList = ArrayList<String>()
        hotCategory.tabInfo.tabList.mapTo(titleList) { it.name }
        val fragmentList = ArrayList<Fragment>()
        hotCategory.tabInfo.tabList.mapTo(fragmentList) { HotDetailFragment(it.apiUrl) }
        val hotViewPagerAdapter = HotViewPagerAdapter(fragmentManager!!, titleList, fragmentList)
        vpMain.adapter = hotViewPagerAdapter
        tablayout.setupWithViewPager(vpMain)
        ViewUtil.setUpIndicatorWidth(tablayout)
    }

    override fun setupToolbar(): Boolean {
//        if (super.setupToolbar()) {
//            return true
//        }
//        super.setupToolbar()
        mActivity.toolbar.setBackgroundColor(0xddffffff.toInt())
        mActivity.iv_search.setImageResource(R.drawable.ic_action_search)
        mActivity.tv_bar_title.text = "热门"
        return true
    }

    var isFirst = true
    override fun onResume() {
        super.onResume()
        if (isFirst) {
            setupToolbar()
            isFirst = false
        }
    }
}