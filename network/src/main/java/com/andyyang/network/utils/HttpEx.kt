package com.andyyang.network.utils

import retrofit2.Retrofit

/**
 *Created by liuyang on 2019/7/24.
 */

inline fun <reified T : Any> Retrofit.create(baseUrl: String): T {
    return newBuilder().baseUrl(baseUrl).build().create(T::class.java)
}