package com.andyyang.eyepetizer.presenter

import com.andyyang.eyepetizer.dataFormat
import com.andyyang.eyepetizer.getNetType
import com.andyyang.eyepetizer.modle.DetailModel
import com.andyyang.eyepetizer.modle.bean.Item
import com.andyyang.eyepetizer.showToast
import com.andyyang.eyepetizer.ui.activity.DetailActivity
import com.andyyang.eyepetizer.ui.base.BasePresenter
import com.andyyang.eyepetizer.utils.C
import io.reactivex.disposables.Disposable

/**
 * Created by AndyYang.
 * data: 2018/2/27.
 * mail: AndyyYang2014@126.com.
 */
class DetailPresenter(view: DetailActivity) : BasePresenter<DetailActivity>(view) {

    var moreReatedUrl: String? = ""
    var moreReplyUrl: String? = ""

    val detailModel: DetailModel by lazy {
        DetailModel()
    }

    fun requestBasicDataFromMemory(itemData: Item): Disposable? {
        //设置背景
        val url = itemData.data?.cover?.blurred + "/thumbnail/${C.getScreenHeight()!! - C.dip2px(250f)!!}x${C.getScreenWidth()}"
        view.setBackground(url)

        val netType = view.getNetType()
        val playInfo = itemData.data?.playInfo
        playInfo?.let {
            if (netType == 1) {
                //wifi
                //设置播放器
                for (playinfo in playInfo) {
                    if (playinfo.type == "high") {
                        val playUrl = playinfo.url
                        view.setPlayer(playUrl)
                        break
                    }
                }
            } else {
                //不是wifi，出提示
                for (playinfo in playInfo) {
                    if (playinfo.type == "normal") {
                        val playUrl = playinfo.url
                        view.setPlayer(playUrl)
                        view.showToast("本次播放消耗${dataFormat(playinfo.urlList[0].size)}流量")

                        break
                    }
                }
            }
        }


        //设置影片信息
        view.setMovieAuthorInfo(itemData)

        return requestRelatedData(itemData.data!!.id)
    }

    fun requestRelatedData(id: Long): Disposable? {
        return detailModel.loadRelatedData(id)
                .subscribe({ view.setRelated(it.itemList) })
    }

    fun requestRelatedAllList(url: String?, title: String): Disposable? {
        view.showDropDownView(title)
        url?.let {
            return detailModel.loadDetailMoreRelatedList(url)
                    .subscribe({
                        moreReatedUrl = it.nextPageUrl
                        view.setDropDownView(it)
                    })
        }
        return null
    }

    fun requestRelatedAllMoreList(): Disposable? {
        moreReatedUrl?.let {
            if (it != "") {
                return detailModel.loadDetailMoreRelatedList(it)
                        .subscribe({
                            moreReatedUrl = it.nextPageUrl
                            view.setMoreDropDownView(it)
                        })
            }
        }
        view.setMoreDropDownView(null)
        return null
    }

    fun requestReply(videoId: Long): Disposable? {
        view.showDropDownView("评论")
        return detailModel.loadReplyList(videoId)
                .subscribe({
                    moreReplyUrl = it.nextPageUrl
                    view.setDropDownView(it)
                })
    }

    fun requestMoreReply(): Disposable? {
        moreReplyUrl?.let {
            if (it != "") {
                return detailModel.loadMoreReplyList(it)
                        .subscribe({
                            moreReplyUrl = it.nextPageUrl
                            view.setMoreDropDownView(it)
                        })
            }
        }
        view.setMoreDropDownView(null)
        return null
    }

}