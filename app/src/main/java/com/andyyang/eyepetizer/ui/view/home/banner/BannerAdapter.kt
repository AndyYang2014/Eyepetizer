package com.andyyang.eyepetizer.ui.view.home.banner

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.toActivityWithSerializable
import com.andyyang.eyepetizer.ui.activity.DetailActivity


/**
 * Created by AndyYang.
 * data: 2018/2/25.
 * mail: AndyyYang2014@126.com.
 */
class BannerAdapter : PagerAdapter() {

    var datas: ArrayList<Item>? = null
    var viewList: ArrayList<HomeBannerItem> = ArrayList()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        if (datas == null) {
            return 0
        } else {
            return datas!!.size
        }
    }


    override fun destroyItem(container: ViewGroup, position: Int,
                             `object`: Any) {
        container.removeView(viewList[position])
        viewList[position].releasePlayer()
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (viewList.size <= position) {
            val homeBannerItem = HomeBannerItem(container.context, datas!![position])
            viewList.add(homeBannerItem)
        }
        val view = viewList[position]
        container.addView(view)
        viewList[position].play()
        view.setOnClickListener { v->v.context.toActivityWithSerializable<DetailActivity>(datas!![position]) }
        return view
    }


}