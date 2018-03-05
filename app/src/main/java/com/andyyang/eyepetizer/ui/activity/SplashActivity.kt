package com.andyyang.eyepetizer.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.net.NetWork
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.C
import com.andyyang.eyepetizer.utils.glide.ImageLoader
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSplashBg()
    }

    private fun getSplashBg() {
        NetWork.api.randomGirl(1).io_main().
                subscribe({ setSplashBg(it.results[0].url) }, { showToast("网络请求失败") })
        C.threadPool.execute({
            SystemClock.sleep(3000)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

    }

    private fun setSplashBg(url: String) {
        ImageLoader.load(this, url, splash_bg)
    }

    override fun noStatusBar(): Boolean {
        return true
    }

}

