package com.andyyang.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.view.detail.DetailInfoItem
import com.andyyang.eyepetizer.ui.view.detail.DetailTextCardView
import com.andyyang.eyepetizer.ui.view.detail.DetailVideoCardView
import com.andyyang.eyepetizer.ui.view.detail.ListEndView
import java.net.URLDecoder

/**
 * Created by AndyYang.
 * data: 2018/2/23.
 * mail: AndyyYang2014@126.com.
 */
class DetailAdapter : BaseAdapter<ViewHolder>() {

    val data by lazy { ArrayList<Item>() }

    private val hasPlayAnimationList by lazy { ArrayList<Int>() }

    /**
     * 只有添加影片信息、作者信息这个item会掉这儿，所以需要先清空数据（比如点了相关推荐的其他item，刷新全部数据，包括影片信息，会先调用这个）
     */
    fun addData(item: Item) {
        data.clear()
        notifyDataSetChanged()
        data.add(item)
        notifyItemInserted(0)
    }

    /**
     * 添加相关推荐item
     */
    fun addData(item: ArrayList<Item>) {
        data.addAll(item)
        notifyItemRangeInserted(1, item.size)
    }

    override fun onBindView(viewHolder: ViewHolder, position: Int) {

        val itemView = viewHolder.itemView
        when (getItemViewType(position)) {
            TYPE_TEXT_CARD -> {
                (itemView as DetailTextCardView).setText(data[position])
                itemView.setOnClickListener { onCategoryTitleClick?.invoke(URLDecoder.decode(data[position].data?.actionUrl?.split("&url=")!![1], "utf-8"), data[position].data?.text) }

            }
            TYPE_VIDEO_CARD -> {
                val hasPlay = hasPlayAnimationList.contains(position)
                if (!hasPlay) {
                    hasPlayAnimationList.add(position)
                }
                (itemView as DetailVideoCardView).setData(data[position], !hasPlay)
                itemView.setOnClickListener { onVideoClick?.invoke(data[position]) }
            }
            TYPE_INFO_CARD -> {
                val hasPlay = hasPlayAnimationList.contains(position)
                if (!hasPlay) {
                    hasPlayAnimationList.add(position)
                }
                (itemView as DetailInfoItem).let {
                    it.setData(data[position], !hasPlay)
                    it.onMovieAuthorClick = onMovieAuthorClick
                }
            }
            TYPE_END_CARD -> {
                (itemView as ListEndView).setShow(data.size > 1)
            }
            else -> {
                throw IllegalArgumentException("日狗，api蒙错了，出现了第三种情况")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView: View
        when (viewType) {
            TYPE_TEXT_CARD -> {
                itemView = DetailTextCardView(parent?.context)
            }
            TYPE_VIDEO_CARD -> {
                itemView = DetailVideoCardView(parent?.context)
            }
            TYPE_INFO_CARD -> {
                itemView = DetailInfoItem(parent?.context)
            }
            TYPE_END_CARD -> {
                itemView = ListEndView(parent?.context)
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            }
            else -> {
                throw IllegalArgumentException("日狗，api蒙错了，出现了第三种情况")
            }
        }
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int) = when (position) {
        0 -> TYPE_INFO_CARD
        itemCount - 1 -> TYPE_END_CARD
        else -> {
            when {
                data[position].type == "textCard" -> TYPE_TEXT_CARD
                data[position].type == "videoSmallCard" -> TYPE_VIDEO_CARD
                else -> throw IllegalArgumentException("日狗，api蒙错了，出现了第三种情况")
            }
        }
    }

    override fun getItemCount() = data.size + 1


    var onVideoClick: ((Item) -> Unit)? = null
    /**
     * 第一个参数为url 第二个是title
     */
    var onCategoryTitleClick: ((String?, String?) -> Unit)? = null

    /**
     * movieinfo authorinfo里的按钮被点击
     */
    var onMovieAuthorClick: ((Int) -> Unit)? = null

    fun setOnItemClick(onVideoClick: (Item) -> Unit,
                       onCategoryTitleClick: (String?, String?) -> Unit,
                       onMovieAuthorClick: (Int) -> Unit) {
        this.onVideoClick = onVideoClick
        this.onCategoryTitleClick = onCategoryTitleClick
        this.onMovieAuthorClick = onMovieAuthorClick
    }

    companion object {
        val TYPE_INFO_CARD = 0
        val TYPE_TEXT_CARD = 1
        val TYPE_VIDEO_CARD = 2
        val TYPE_END_CARD = 3
    }

}