package com.andyyang.eyepetizer.ui.view.detail

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.modle.bean.Item
import kotlinx.android.synthetic.main.layout_detail_reply_title_card.view.*

/**
 * Created by AndyYang.
 * data: 2018/2/28.
 * mail: AndyyYang2014@126.com.
 */
class DetailReplyTitleView : LinearLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.layout_detail_reply_title_card, this)
        this.tv_text_card.setTextColor(Color.WHITE)
        this.tv_text_card.textSize = 35f
    }

    fun setText(item: Item?) {
        var result = ""
        item?.data?.text
                ?.split("")
                ?.filter { it != "" }
                ?.forEach { result = result + it + "  " }
        this.tv_text_card.text = result

        this.hasMore.visibility = if (item?.data?.actionUrl != null) View.VISIBLE else View.INVISIBLE
    }
}