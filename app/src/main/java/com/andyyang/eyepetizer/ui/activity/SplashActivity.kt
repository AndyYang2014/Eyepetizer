package com.andyyang.eyepetizer.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.displayUrl
import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.net.NetWork
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.PermissionHelper
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.doAsync

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class SplashActivity : BaseActivity() {

    lateinit private var mPermissionHelper: PermissionHelper

    override fun getActivityLayoutId() = R.layout.activity_splash

    override fun initActivity(savedInstanceState: Bundle?) {
        mPermissionHelper = PermissionHelper(this)
        mPermissionHelper.setOnApplyPermissionListener { runApp() }
        if (Build.VERSION.SDK_INT < 23) {
            runApp()
        } else {
            if (mPermissionHelper.isAllRequestedPermissionGranted) {
                runApp()
            } else {
                mPermissionHelper.applyPermissions()
            }
        }
    }

    private fun runApp() {
        NetWork.api.randomGirl(1).io_main().
                subscribe({ setSplashBg(it.results[0].url) }, { showToast("网络请求失败") })
        doAsync {
            SystemClock.sleep(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun setSplashBg(url: String) = splash_bg.displayUrl(url)

    override fun noStatusBar() = true

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPermissionHelper.onActivityResult(requestCode, resultCode, data)
    }

}

