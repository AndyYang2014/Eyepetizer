package com.andyyang.eyepetizer.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.displayUrl
import com.andyyang.eyepetizer.interfaces.onBitmapSavedListener
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.Logger
import com.andyyang.eyepetizer.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_bigimg.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by AndyYang.
 * data: 2018/3/3.
 * mail: AndyyYang2014@126.com.
 */

class BigImageActivity : BaseActivity() {

    override fun getActivityLayoutId() = R.layout.activity_bigimg

    override fun initActivity(savedInstanceState: Bundle?) {
        with(bigimg_content) {
            displayUrl(intent.getStringExtra("imgUrl"))
            onClick {
                if (bigimg_btn_save.visibility == View.GONE) {
                    bigimg_btn_save.visibility = View.VISIBLE
                } else {
                    bigimg_btn_save.visibility = View.GONE
                }
            }
        }

        with(bigimg_btn_save) {
            onClick {
                this@with.isEnabled = false
                bigimg_content.isDrawingCacheEnabled = true
                bigimg_content.buildDrawingCache()
                val drawingCache = bigimg_content.drawingCache
                ImageLoader.saveBitmap2Store(this@BigImageActivity, drawingCache, object : onBitmapSavedListener {
                    override fun onSuccess() {
                        showToast("图片已保存至本地")
                        this@with.visibility = View.GONE
                        this@with.isEnabled = true
                        bigimg_content.destroyDrawingCache()
                    }

                    override fun onFaiure(e: Exception) {
                        Logger.e(e.toString())
                        showToast(e.toString())
                        this@with.isEnabled = true
                        bigimg_content.destroyDrawingCache()
                    }
                })
            }
        }
    }

    override fun noStatusBar() = true

    companion object {
        fun getStartIntent(context: Context, imgUrl: String): Intent {
            val intent = Intent(context, BigImageActivity::class.java)
            intent.putExtra("imgUrl", imgUrl)
            return intent
        }
    }
}
