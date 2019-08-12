package com.andyyang.network.utils.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.andyyang.network.utils.ssl.TrustAllHostnameVerifier
import com.andyyang.network.utils.ssl.TrustAllManager
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 *Created by liuyang on 2019/7/17.
 */
@GlideModule
class OkHttpAppGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okhttp = OkHttpClient.Builder()
            .sslSocketFactory(TrustAllManager.createSSLSocketFactory(),TrustAllManager())
            .hostnameVerifier(TrustAllHostnameVerifier())
            .build()
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okhttp))
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }


}