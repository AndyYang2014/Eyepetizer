package com.andyyang.eyepetizer.ui.activity

import android.os.Bundle
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.ui.fragment.CategoryFragment
import com.andyyang.eyepetizer.ui.fragment.GirlsFragment
import com.andyyang.eyepetizer.ui.fragment.HomeFragment
import com.andyyang.eyepetizer.ui.fragment.HotFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by AndyYang.
 * data: 2018/2/13.
 * mail: AndyyYang2014@126.com.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        main_navigation.setMode(BottomNavigationBar.MODE_DEFAULT)
        main_navigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)

        main_navigation
                .addItem(BottomNavigationItem(R.drawable.ic_tab_strip_icon_feed, "开眼精选").setActiveColorResource(R.color.colorTab))
                .addItem(BottomNavigationItem(R.drawable.ic_tab_strip_icon_category, "分类").setActiveColorResource(R.color.colorTab))
                .addItem(BottomNavigationItem(R.drawable.ic_tab_strip_icon_follow, "热门").setActiveColorResource(R.color.colorTab))
                .addItem(BottomNavigationItem(R.drawable.ic_beauty, "妹子").setActiveColorResource(R.color.colorTab))
                .setFirstSelectedPosition(0)
                .initialise()

        setDefaultFragment()
        main_navigation.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                when (position) {
                    0 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_layFrame, HomeFragment()).commitAllowingStateLoss()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_layFrame, CategoryFragment()).commitAllowingStateLoss()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_layFrame, HotFragment()).commitAllowingStateLoss()
                    }
                    3 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_layFrame, GirlsFragment()).commitAllowingStateLoss()
                    }
                }
            }
        })
    }

    private fun setDefaultFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_layFrame, HomeFragment())
        transaction.commit()
    }
}
