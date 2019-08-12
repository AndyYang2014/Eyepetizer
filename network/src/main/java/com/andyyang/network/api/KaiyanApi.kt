package com.andyyang.network.api

import com.andyyang.network.entity.Category
import com.andyyang.network.entity.HomeBean
import com.andyyang.network.entity.HotCategory
import com.andyyang.network.entity.Issue
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.ArrayList

/**
 *Created by liuyang on 2019/8/12.
 */
interface KaiyanApi {

    @GET("v2/feed?&num=1")
    fun getFirstHomeData(@Query("date") date: Long): Flowable<HomeBean>

    @GET
    fun getMoreHomeData(@Url url: String): Flowable<HomeBean>

    @GET
    fun getIssue(@Url url: String): Flowable<Issue>

    @GET
    fun getHotCategory(@Url url: String): Flowable<HotCategory>

    @GET("v2/replies/video?")
    fun getReply(@Query("videoId") videoId: Long): Flowable<Issue>

    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Flowable<Issue>

    @GET("v4/categories")
    fun getCategory(): Flowable<ArrayList<Category>>

    @GET("v4/categories/videoList")
    fun getCategoryItemList(@Query("id") id: Long): Flowable<Issue>

}