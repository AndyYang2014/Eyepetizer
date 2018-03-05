package com.andyyang.eyepetizer.utils.glide

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView

import java.io.File
import java.io.FileOutputStream

import android.R.attr.path
import com.andyyang.eyepetizer.interfaces.onBitmapSavedListener
import com.bumptech.glide.Glide

/**
 * Created by AndyYang
 * date:2018/2/18.
 * mail:andyyang2014@126.com
 */

object ImageLoader {

    private var loader: ImageLoader? = null

    fun load(context: Context, url: String, view: ImageView) {
        if (loader == null) {
            synchronized(ImageLoader::class.java) {
                if (loader == null) {
                    loader = ImageLoader
                }
            }
        }
        Glide.with(context)
                .load(QiNiuUtils.setUrl(url, 320))
                .centerCrop()
                .crossFade()
                .into(view)
    }

    fun saveBitmap2Store(context: Context, bitmap: Bitmap, listener: onBitmapSavedListener) {
        try {
            val appDir = File(Environment.getExternalStorageDirectory(), "Eyepetizer")
            if (!appDir.exists()) {
                appDir.mkdir()
            }
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val file = File(appDir, fileName)
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, fileName, null)
            context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)))
            listener.onSuccess()
        } catch (e: Exception) {
            listener.onFaiure(e)
            e.printStackTrace()
        }

    }

}
