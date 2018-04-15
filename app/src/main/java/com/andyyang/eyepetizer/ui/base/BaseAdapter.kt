package com.andyyang.eyepetizer.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.andyyang.eyepetizer.ui.base.BaseAdapter.BaseViewHolder

/**
 * Created by AndyYang
 * date:2018/2/14.
 * mail:andyyang2014@126.com
 */

abstract class BaseAdapter<in T : BaseViewHolder> : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.view.setOnClickListener {
            onItemClick?.invoke(position)

        }
        holder.view.setOnLongClickListener {
            onLongItemClick?.invoke(position)
            true
        }

        onBindView(holder as T, position)
    }

    protected abstract fun onBindView(viewHolder: T, position: Int)

    var onItemClick: ((Int) -> Unit)? = null

    var onLongItemClick: ((Int) -> Unit)? = null

    open class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}

