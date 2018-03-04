package com.andyyang.eyepetizer.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.interfaces.onBitmapSavedListener
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.glide.ImageLoader
import kotlinx.android.synthetic.main.activity_bigimg.*


/**
 * Created by AndyYang.
 * data: 2018/3/3.
 * mail: AndyyYang2014@126.com.
 */

class BigImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bigimg)
        init()
    }

    private fun init() {
        ImageLoader.load(this, intent.getStringExtra("imgUrl"), bigimg_content)
        bigimg_content.setOnClickListener {
            if (bigimg_btn_save.visibility == View.GONE) {
                bigimg_btn_save.visibility = View.VISIBLE
            } else {
                bigimg_btn_save.visibility = View.GONE
            }
        }

        bigimg_btn_save.setOnClickListener {
            bigimg_btn_save.isEnabled = false
            val drawingCache = bigimg_content.drawingCache
            ImageLoader.saveBitmap2Store(this@BigImageActivity, drawingCache, object : onBitmapSavedListener {
                override fun onSuccess() {
                    showToast("图片已保存至本地")
                    bigimg_btn_save.visibility = View.GONE
                    bigimg_btn_save.isEnabled = true
                }

                override fun onFaiure() {
                    showToast("图片保存失败")
                    bigimg_btn_save.isEnabled = true
                }
            })
        }
    }

    override fun noStatusBar(): Boolean {
        return true
    }


    companion object {
        fun getStartIntent(context: Context, imgUrl: String): Intent {
            val intent = Intent(context, BigImageActivity::class.java)
            intent.putExtra("imgUrl", imgUrl)
            return intent
        }
    }
}
