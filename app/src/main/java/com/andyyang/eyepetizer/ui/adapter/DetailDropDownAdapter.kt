package com.andyyang.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.view.detail.DetailReplyTitleView
import com.andyyang.eyepetizer.ui.view.detail.DetailReplyView
import com.andyyang.eyepetizer.ui.view.detail.DetailVideoCardView
import com.andyyang.eyepetizer.ui.view.detail.ListEndView

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class DetailDropDownAdapter : BaseAdapter<ViewHolder>() {

    val data by lazy {
        ArrayList<Item>()
    }

    override fun onBindView(viewHolder: com.andyyang.eyepetizer.ui.base.ViewHolder, position: Int) {

        val itemView = viewHolder.itemView
        when (getItemViewType(position)) {
            TYPE_VIDEO -> {
                (itemView as DetailVideoCardView).setData(data[position], false)
                itemView.setOnClickListener { onVideoClick?.invoke(data[position]) }
            }
            TYPE_REPLY_TITLE -> {
                (itemView as DetailReplyTitleView).setText(data[position])
            }
            TYPE_REPLY -> {
                (itemView as DetailReplyView).setData(data[position])
            }
            else -> {
                throw IllegalArgumentException("日狗，api蒙错了，出现了第三种情况")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView: View
        when (viewType) {
            TYPE_VIDEO -> {
                itemView = DetailVideoCardView(parent?.context)
            }
            TYPE_REPLY -> {
                itemView = DetailReplyView(parent?.context)
            }
            TYPE_END -> {
                itemView = ListEndView(parent?.context)
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            }
            TYPE_REPLY_TITLE -> {
                itemView = DetailReplyTitleView(parent?.context)
            }
            else -> {
                throw IllegalArgumentException("日狗，api蒙错了，赶紧改")
            }
        }
        return ViewHolder(itemView)
    }

    override fun getItemCount()  = data.size

    override fun getItemViewType(position: Int): Int {
        if (data[position].data == null) {
            return TYPE_END
        }
        when (data[position].type) {
            "reply" -> {
                return TYPE_REPLY
            }
            "leftAlignTextHeader" -> {
                return TYPE_REPLY_TITLE
            }
            "videoSmallCard" -> {
                return TYPE_VIDEO
            }
        }
        return super.getItemViewType(position)
    }

    fun addData(items: ArrayList<Item>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(item: Item) {
        if (data[data.size - 1].data == null) {//最后一个数据是空的情况只能有一个，用来展示the end
            return
        }
        data.add(item)
        notifyDataSetChanged()
    }

    fun setData(items: ArrayList<Item>) {
        data.clear()
        notifyDataSetChanged()
        addData(items)
    }

    var onVideoClick: ((Item) -> Unit)? = {}

    companion object {
        val TYPE_VIDEO = 1
        val TYPE_REPLY = 2
        val TYPE_END = 3
        val TYPE_REPLY_TITLE = 4//最新评论、热门评论
    }

}