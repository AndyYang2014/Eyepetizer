package com.andyyang.eyepetizer.net

import com.andyyang.eyepetizer.modle.bean.*
import com.andyyang.eyepetizer.utils.C.Url.Companion.GIRL
import com.andyyang.eyepetizer.utils.C.Url.Companion.RANDOM
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by AndyYang.
 * data: 2018/2/15.
 * mail: AndyyYang2014@126.com.
 */

interface RequestApi {

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

    @GET(RANDOM + "/{number}")
    fun randomGirl(@Path("number") number: Int): Flowable<GankInfo>

    @GET(GIRL + "/{number}/{page}")
    fun girls(@Path("number") number: Int, @Path("page") page: Int): Flowable<GirlsList>
}
