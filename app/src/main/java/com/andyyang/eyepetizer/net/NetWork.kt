package com.andyyang.eyepetizer.net

import com.andyyang.eyepetizer.utils.C.Url.Companion.base_url
import com.andyyang.eyepetizer.utils.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by AndyYang.
 * data: 2018/2/15.
 * mail: AndyyYang2014@126.com.
 */

object NetWork {
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient
    private val DEFAULT_TIMEOUT = 20L

    init {
        val longging = Interceptor { chain ->
            val request = chain.request()
            Logger.e("okhttp", "okhttp--->" + request.url().toString())
            chain.proceed(request)
        }

        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(longging)
                .build()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(base_url)
                .build()
    }

    val api: RequestApi by lazy {
        retrofit.create(RequestApi::class.java)
    }

}