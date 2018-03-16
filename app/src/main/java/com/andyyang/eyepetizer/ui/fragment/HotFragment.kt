package com.andyyang.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
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
    val presenter: HotFragmentPresenter = HotFragmentPresenter(this)

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        presenter.requestHotCategory()
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_hot
    }

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


    fun onError() {
    }



    override fun setupToolbar(): Boolean {
//        if (super.setupToolbar()) {
//            return true
//        }
//        super.setupToolbar()
        activity.toolbar.setBackgroundColor(0xddffffff.toInt())
        activity.iv_search.setImageResource(R.drawable.ic_action_search)
        activity.tv_bar_title.text = "热门"
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