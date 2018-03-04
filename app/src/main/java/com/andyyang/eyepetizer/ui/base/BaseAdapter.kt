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

    private var listener: OnItemClickListener? = null
    private var Longlistener: OnLongClickListener? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val index = position
        holder.view.setOnClickListener { v ->
            listener?.let { listener!!.onItemClick(v, index) }

        }
        holder.view.setOnLongClickListener { v ->
            Longlistener?.let { Longlistener!!.onLongItemClick(v, index) }
            true
        }

        onBindView(holder as T, position)
    }

    protected abstract fun onBindView(viewHolder: T, position: Int)

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setOnLongClickListener(listener: OnLongClickListener) {
        this.Longlistener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface OnLongClickListener {
        fun onLongItemClick(v: View, position: Int)
    }

    open class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}

