package com.andyyang.eyepetizer.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */

class HotViewPagerAdapter(supportFragmentManager: FragmentManager, private var titleList: ArrayList<String>?,
                          private var fragmentList: ArrayList<Fragment>?) : FragmentPagerAdapter(supportFragmentManager) {

    override fun getCount() = fragmentList?.size ?: 0

    override fun getPageTitle(position: Int) = titleList!![position]

    override fun getItem(position: Int) = fragmentList!![position]

}
