package com.andyyang.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.toActivityWithSerializable
import com.andyyang.eyepetizer.ui.activity.DetailActivity
import com.andyyang.eyepetizer.ui.view.hot.HotItem
import com.andyyang.eyepetizer.ui.view.detail.ListEndView
import kotlinx.android.synthetic.main.layout_list_end.view.*

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class HotCategoryAdapter : RecyclerView.Adapter<HotCategoryAdapter.ViewHolder>() {
    val data: ArrayList<Item> by lazy { ArrayList<Item>() }

    fun addItemList(itemList: ArrayList<Item>) {
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    val TYPE_STANDARD = 1
    val TYPE_END = 2
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (getItemViewType(position) == TYPE_STANDARD) {
            (holder?.itemView as HotItem).setData(data[position])
            holder.itemView!!.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(data[position]) }
        }
    }

    override fun getItemCount(): Int = if (data.size == 0) 0 else data.size + 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var itemView: View? = null

        when (viewType) {
            TYPE_STANDARD -> {
                itemView = (HotItem(parent?.context))
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            }
            TYPE_END -> {
                itemView = ListEndView(parent?.context)
                itemView.tv_text_end.setTextColor(0xff000000.toInt())
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            }
        }
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) {
            TYPE_END
        } else {
            TYPE_STANDARD
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}