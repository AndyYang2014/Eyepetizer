package com.andyyang.eyepetizer.ui.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.Category
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.view.category.CategoryItem
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.view.detail.ListEndView
import kotlinx.android.synthetic.main.layout_list_end.view.*
import java.util.*

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class CategoryAdapter : BaseAdapter<ViewHolder>() {

    val data by lazy {
        ArrayList<Category>()
    }

    override fun onBindView(viewHolder: ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_STANDARD -> {
                (viewHolder.itemView as CategoryItem).setData(data[position])
                viewHolder.itemView.setOnClickListener { onClick?.invoke(data[position]) }
            }
        }
    }

    fun setData(data: ArrayList<Category>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if (data.size == 0) 0 else data.size + 1

    private val TYPE_STANDARD = 1
    private val TYPE_END = 2

    override fun getItemViewType(position: Int): Int {
        if (data.size == position)
            return TYPE_END
        return TYPE_STANDARD
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var itemView: View? = null
        when (viewType) {
            TYPE_END -> {
                itemView = ListEndView(parent?.context)
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                itemView.tv_text_end.setTextColor(Color.BLACK)
            }

            TYPE_STANDARD -> {
                itemView = CategoryItem(parent?.context)
            }
        }
        return ViewHolder(itemView!!)
    }

    var onClick: ((Category) -> Unit)? = {}
}