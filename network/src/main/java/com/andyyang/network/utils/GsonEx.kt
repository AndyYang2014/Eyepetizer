package com.andyyang.network.utils

import com.google.gson.Gson

/**
 *Created by liuyang on 2019/7/24.
 */
private val GSON: Gson by lazy {
    Gson()
}

fun Any.toJson(): String {
    return GSON.toJson(this)
}

fun String.toMap(): Map<*, *> {
    return GSON.fromJson(this, Map::class.java)
}

fun <T> String.toBean(classOf: Class<T>): T {
    return GSON.fromJson<T>(this, classOf)
}