package com.andyyang.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.toActivityWithSerializable
import com.andyyang.eyepetizer.ui.activity.DetailActivity
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.view.common.StandardVideoItem
import com.andyyang.eyepetizer.ui.view.home.HomeTextHeaderItem
import com.andyyang.eyepetizer.ui.view.home.banner.HomeBanner
import com.andyyang.eyepetizer.utils.C

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class HomeAdapter : BaseAdapter<ViewHolder>() {

    override fun onBindView(viewHolder: ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_BANNER -> {
                if (isNewBanner) {
                    isNewBanner = false
                    (viewHolder.itemView as HomeBanner).setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
                }
            }
            TYPE_STANDARD -> (viewHolder.itemView as StandardVideoItem).let {
                it.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(itemList[position + bannerItemListCount - 1]) }
                it.setData(itemList[position + bannerItemListCount - 1], "feed")
            }

            TYPE_HEADER_TEXT -> (viewHolder.itemView as HomeTextHeaderItem).setHeaderText(itemList[position + bannerItemListCount - 1].data?.text)

        }
    }


    private var isNewBanner = false

    //只会在banner数据请求到的时候set，其他都是add，所以通过set可以获取到banner的count
    var itemList: ArrayList<Item> = ArrayList()
        set(value) {
            field = value
            isNewBanner = true
            notifyDataSetChanged()
        }

    //banner用了的item的数量（包括type为banner2的）
    var bannerItemListCount = 0

    fun addData(itemList: ArrayList<Item>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = when {
        itemList.size > bannerItemListCount -> itemList.size - bannerItemListCount + 1
        itemList.size == 0 -> 0
        else -> 1
    }

    override fun getItemViewType(position: Int) = when {
        position == 0 -> TYPE_BANNER
        itemList[position + bannerItemListCount - 1].type == "textHeader" -> TYPE_HEADER_TEXT
        else -> TYPE_STANDARD
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        TYPE_BANNER -> ViewHolder(HomeBanner(parent!!.context))

        TYPE_STANDARD -> {
            val textView = StandardVideoItem(parent!!.context)
            ViewHolder(textView)
        }
        TYPE_HEADER_TEXT -> {
            val headerText = HomeTextHeaderItem(parent!!.context)
            headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    C.dip2px(56f)!!)
            ViewHolder(headerText)
        }
        else -> null
    }


    fun setBannerSize(size: Int) {
        bannerItemListCount = size
    }

    companion object {
        val TYPE_BANNER = 1
        val TYPE_STANDARD = 2
        val TYPE_HEADER_TEXT = 3
    }

}