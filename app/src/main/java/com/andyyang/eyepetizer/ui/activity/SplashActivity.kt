package com.andyyang.eyepetizer.ui.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
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
import org.jetbrains.anko.doAsync
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
        val scaleAnimation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 2000
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

        val cachefile = File(appDir, "cache.txt")
        if (cachefile.exists()) {
            isCache = true
            val path = FileUtils.readFile2String(cachefile, "UTF-8")
            val bitmap = BitmapFactory.decodeFile(appDir!!.absolutePath + File.separator + path.trim())
            splash_bg.setImageBitmap(bitmap)
            setAnimation()
        }
    }


    private fun setSplashBg(result: GankInfo.Result) {
        if (!isCache) {
            splash_bg.displayUrl(result.url)
            doAsync {
                SystemClock.sleep(2000)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }

        val type = result.url.split(".")
        val savename = result._id + "." + type[type.size - 1]
        val downloadfile = File(appDir, savename)
        if (downloadfile.exists()) {
            downloadfile.delete()
        }
        RxDownload.getInstance()
                .download(result.url, savename, appDir!!.absolutePath)
                .io_main()
                .subscribe({}, {}, {
                    val cachefile = File(appDir, "cache.txt")
                    if (cachefile.exists()) {
                        cachefile.delete()
                    }
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

