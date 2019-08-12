package com.andyyang.eyepetizer.modle

import com.andyyang.eyepetizer.modle.bean.Issue
import io.reactivex.Flowable

/**
 * Created by AndyYang.
 * data: 2018/2/24.
 * mail: AndyyYang2014@126.com.
 */
class DetailModel {

    fun loadRelatedData(id: Long): Flowable<Issue> {
        return NetWork.api.getRelatedData(id).io_main()
    }

    fun loadDetailMoreRelatedList(url: String): Flowable<Issue> {
        return NetWork.api.getIssue(url).io_main()
    }

    fun loadReplyList(videoId: Long): Flowable<Issue> {
        return NetWork.api.getReply(videoId).io_main()
    }

    fun loadMoreReplyList(url: String): Flowable<Issue> {
        return NetWork.api.getIssue(url).io_main()
    }
}