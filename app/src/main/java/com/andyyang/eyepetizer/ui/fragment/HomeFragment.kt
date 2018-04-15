package com.andyyang.eyepetizer.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.HomeBean
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.presenter.HomePresenter
import com.andyyang.eyepetizer.ui.adapter.HomeAdapter
import com.andyyang.eyepetizer.ui.base.BaseFragment
import com.andyyang.eyepetizer.ui.view.home.banner.HomeBanner
import com.andyyang.eyepetizer.ui.view.home.banner.HomeBannerItem
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by AndyYang.
 * data: 2018/2/14.
 * mail: AndyyYang2014@126.com.
 */
class HomeFragment : BaseFragment() {

    private val simpleDateFormat by lazy { SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH) }
    private lateinit var homeAdapter: HomeAdapter
    val presenter by lazy { HomePresenter(this) }
    var loadingMore = false

    override fun initFragment(savedInstanceState: Bundle?) {
        initView()
        presenter.requestFirstData()
    }

    override fun getFragmentLayoutId() = R.layout.fragment_home

    private fun initView() {
        mActivity.tv_bar_title?.typeface = Typeface.createFromAsset(mActivity.assets, "fonts/Lobster-1.4.otf")
        val paint = mActivity.tv_bar_title.paint
        paint.isFakeBoldText = true

        homeAdapter = HomeAdapter()
        with(home_rv) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
            onRefresh = {
                presenter.requestFirstData()
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val childCount = home_rv.childCount
                        val itemCount = home_rv.layoutManager.itemCount
                        val firstVisibleItem = (home_rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (firstVisibleItem + childCount == itemCount) {
                            if (!loadingMore) {
                                loadingMore = true
                                onLoadMore()
                            }
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    setupToolbar()
                }
            })
        }

    }

    private val linearLayoutManager by lazy {
        home_rv.layoutManager as LinearLayoutManager
    }

    /**
     * recyclerview滚动的时候会调用这里，在这里设置toolbar
     */
    override fun setupToolbar(): Boolean {
//        if (super.setupToolbar()) {
//            return true
//        }
        val findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        if (findFirstVisibleItemPosition == 0) {//设置为透明
            mActivity.toolbar.setBackgroundColor(0x00000000)
            mActivity.iv_search.setImageResource(R.drawable.ic_action_search_white)
            mActivity.tv_bar_title.text = ""

        } else {
            if (homeAdapter.itemList.size > 1) {

                mActivity.toolbar.setBackgroundColor(0xddffffff.toInt())
                mActivity.iv_search.setImageResource(R.drawable.ic_action_search)
                val itemList = homeAdapter.itemList
                val item = itemList[findFirstVisibleItemPosition + homeAdapter.bannerItemListCount - 1]
                if (item.type == "textHeader") {
                    mActivity.tv_bar_title.text = item.data?.text
                } else {
                    mActivity.tv_bar_title.text = simpleDateFormat.format(item.data?.date)
                }
            }

        }
        return true
    }

    fun onLoadMore() = presenter.requestMoreData()

    fun setMoreData(itemList: ArrayList<Item>) {
        loadingMore = false
        homeAdapter.addData(itemList)
    }

    fun setFirstData(homeBean: HomeBean) {
        homeAdapter.setBannerSize(homeBean.issueList[0].count)
        homeAdapter.itemList = homeBean.issueList[0].itemList
        home_rv.hideLoading()
    }

    fun onError() = home_rv.hideLoading()

    override fun onResume() {
        super.onResume()
        if (home_rv.getChildAt(0) is HomeBanner) {
            (0 until (home_rv.getChildAt(0) as HomeBanner).viewPager.childCount)
                    .map { (home_rv.getChildAt(0) as HomeBanner).viewPager.getChildAt(it) as HomeBannerItem }
                    .filter { it.isVideo }
                    .forEach {
                        it.setUpView()//重新设置视频
                    }
        }
    }

    override fun onPause() {
        super.onPause()
        GSYVideoPlayer.releaseAllVideos()
    }

}