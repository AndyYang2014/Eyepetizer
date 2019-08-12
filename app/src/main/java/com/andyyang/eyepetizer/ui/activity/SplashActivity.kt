package com.andyyang.eyepetizer.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.io_main
import com.andyyang.eyepetizer.modle.bean.GankInfo
import com.andyyang.eyepetizer.ui.base.BaseActivity
import com.andyyang.eyepetizer.utils.FileUtils
import com.andyyang.eyepetizer.utils.PermissionHelper
import zlc.season.rxdownload2.RxDownload
import java.io.File


/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class SplashActivity : BaseActivity() {

    private var appDir: File? = null
    private var isCache: Boolean = false
    private val permissionModels by lazy {
        arrayOf(
                PermissionHelper.PermissionModel("存储空间", Manifest.permission.WRITE_EXTERNAL_STORAGE, "我们需要您允许我们读写你的存储卡，以方便我们临时保存一些数据", WRITE_EXTERNAL_STORAGE_CODE)
        )
    }

    override fun getActivityLayoutId() = R.layout.activity_splash

    override fun initActivity(savedInstanceState: Bundle?) {
        RequestPermissions(permissionModels, onApply = {
            runApp()
        })
    }

    private fun runApp() {
        initFile()
        NetWork.api.randomGirl(1).io_main().
                subscribe({ download(it.results[0]) }, { })
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
            val imgpath = File(appDir!!.absolutePath + File.separator, path)
            if (imgpath.exists()) {
                val bitmap = BitmapFactory.decodeFile(appDir!!.absolutePath + File.separator + path.trim())
                splash_bg.setImageBitmap(bitmap)
                setAnimation()
            } else {
                showDefaultImg()
            }
        } else {
            showDefaultImg()
        }
    }

    private fun showDefaultImg() {
        splash_bg.setImageResource(R.drawable.splash_default)
        setAnimation()
    }


    private fun download(result: GankInfo.Result) {
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

    companion object {
        val WRITE_EXTERNAL_STORAGE_CODE = 101
    }

}

