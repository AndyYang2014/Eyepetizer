package com.andyyang.network.api

import com.andyyang.network.entity.GankInfo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by liuyang on 2019/8/12.
 */
interface GankApi {

    @GET( "random/data/福利/{number}")
    fun randomGirl(@Path("number") number: Int): Flowable<GankInfo>

    @GET( "data/福利/{number}/{page}")
    fun girls(@Path("number") number: Int, @Path("page") page: Int): Flowable<GankInfo>

}