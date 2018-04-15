package com.andyyang.eyepetizer.ui.adapter

import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.toActivityWithSerializable
import com.andyyang.eyepetizer.ui.activity.DetailActivity
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.view.common.StandardVideoItem

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */

class CategoryDetailAdapter : BaseAdapter<ViewHolder>() {
    private val categorys: ArrayList<Item> by lazy {
        ArrayList<Item>()
    }

    fun addData(itemList: ArrayList<Item>) {
        this.categorys.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(StandardVideoItem(parent!!.context))
    }

    override fun getItemCount() = categorys.size

    override fun onBindView(viewHolder: ViewHolder, position: Int) {
        (viewHolder.itemView as StandardVideoItem).let {
            it.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(categorys[position]) }
            it.setData(categorys[position], "categorydetail")
        }
    }
}
