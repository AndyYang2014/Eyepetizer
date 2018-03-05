package com.andyyang.eyepetizer.ui.adapter


import android.view.ViewGroup
import com.andyyang.eyepetizer.modle.bean.GirlsList
import com.andyyang.eyepetizer.ui.activity.BigImageActivity
import com.andyyang.eyepetizer.ui.base.BaseAdapter
import com.andyyang.eyepetizer.ui.base.ViewHolder
import com.andyyang.eyepetizer.ui.view.girls.GirlsItemView
import java.util.*

/**
 * Created by AndyYang.
 * data: 2018/3/3.
 * mail: AndyyYang2014@126.com.
 */

class GirlsAdapter : BaseAdapter<ViewHolder>() {

    val data by lazy {
        ArrayList<GirlsList.Result>()
    }

    override fun onBindView(viewHolder: ViewHolder, position: Int) {
        (viewHolder.view as GirlsItemView).bindData(data[position])
        viewHolder.itemView.setOnClickListener {
            it.context.startActivity(BigImageActivity.getStartIntent(it.context, data[position].url))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.BaseViewHolder {
        return ViewHolder(GirlsItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun upData(newDatas: List<GirlsList.Result>, init: Boolean) {
        if (init) data.clear()
        this.data.addAll(newDatas)
        notifyDataSetChanged()
    }
}

