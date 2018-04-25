package com.andyyang.eyepetizer.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.displayUrl
import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.modle.bean.GankInfo
import com.andyyang.eyepetizer.net.NetWork
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.FileUtils
import com.andyyang.eyepetizer.utils.PermissionHelper
import kotlinx.android.synthetic.main.activity_splash.*
import zlc.season.rxdownload2.RxDownload
import java.io.File

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class SplashActivity : BaseActivity() {

    lateinit private var mPermissionHelper: PermissionHelper
    private var appDir: File? = null
    private var isCache: Boolean = false

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
        initFile()
        NetWork.api.randomGirl(1).io_main().
                subscribe({ setSplashBg(it.results[0]) }, { showToast("网络请求失败") })
    }

    private fun setAnimation() {
        val scaleAnimation = ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1500
        splash_bg.startAnimation(scaleAnimation)
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun initFile() {
        appDir = File(Environment.getExternalStorageDirectory(), "Eyepetizer" + File.separator + "images")
        if (!appDir!!.exists()) {
            appDir!!.mkdirs()
        }
//        val filesInDir = FileUtils.listFilesInDir(appDir!!.absolutePath)
//        filesInDir.forEach {
//            Log.e("file", "file://" + it)
//
//        }
        val cachefile = File(appDir, "cache.txt")
        if (cachefile.exists()) {
            isCache = true
            val path = FileUtils.readFile2String(cachefile, "UTF-8")
            Log.e("file", "file://" + appDir!!.absolutePath + File.separator + path)

            splash_bg.displayUrl("file://" + appDir!!.absolutePath + File.separator + path.trim())
            setAnimation()
            FileUtils.deleteFilesInDir(appDir)
        }
    }


    private fun setSplashBg(result: GankInfo.Result) {
        if (!isCache) {
            splash_bg.displayUrl(result.url)
        }

        val type = result.url.split(".")
        val savename = result._id + "." + type[type.size - 1]

        RxDownload.getInstance()
                .download(result.url, savename, appDir!!.absolutePath)
                .io_main()
                .subscribe({}, {}, {
                    val cachefile = File(appDir, "cache.txt")
                    FileUtils.writeFileFromString(cachefile, savename, true)
                })
    }

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

