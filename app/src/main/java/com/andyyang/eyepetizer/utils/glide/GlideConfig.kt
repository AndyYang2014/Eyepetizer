package com.andyyang.eyepetizer.utils.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl

import java.io.InputStream


/**
 * Created by AndyYang
 * date:2018/2/18.
 * mail:andyyang2014@126.com
 */
class GlideConfig : OkHttpGlideModule() {
    internal var diskSize = 1024 * 1024 * 100 // 100kb本地内存
    internal var memorySize = Runtime.getRuntime().maxMemory().toInt() / 8  // 取1/8最大内存作为最大缓存

    override fun applyOptions(context: Context?, builder: GlideBuilder) {
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskSize))  // 内存中

        builder.setDiskCache(ExternalCacheDiskCacheFactory(context, "cache", diskSize)) // sd卡中

        // 自定义内存和图片池大小
        builder.setMemoryCache(LruResourceCache(memorySize)) // 自定义内存大小
        builder.setBitmapPool(LruBitmapPool(memorySize)) // 自定义图片池大小

        // 定义图片格式
        // builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888); // 高质量
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565) // 默认(中等质量)
    }

    override fun registerComponents(context: Context?, glide: Glide) {
        glide.register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())//将底层网络框架替换为okhttp
    }
}