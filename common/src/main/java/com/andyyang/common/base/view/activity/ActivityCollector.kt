package com.andyyang.common.base.view.activity

import android.app.Activity
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.*

/**
 *Created by liuyang on 2019/5/05.
 */
object ActivityCollector {

    private var activities: MutableList<Activity> = ArrayList()

    val currentActivity: Activity
        get() = activities[activities.size - 1]

    fun getTaskActivity(index: Int): Activity {
        return activities[activities.size - 1 - index]
    }

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        activities.filter {
            !it.isFinishing
        }.forEach {
            it.finish()
        }
    }

    fun finishActivity(tClass: Class<*>) {
        activities.filter { it.javaClass == tClass }.forEach { it.finish() }
    }

    fun finishActivitys(vararg tClass: Class<*>) {
        tClass.toObservable()
                .filter {
                    tClass.isNotEmpty()
                }
                .flatMap { t ->
                    activities.toObservable()
                            .filter {
                                t == it.javaClass
                            }
                }
                .subscribeBy {
                    it.finish()
                }
    }

    fun isExistActivity(activity: String): Boolean {
        for (act in activities) {
            if (act.localClassName == activity) {
                return true
            }
        }
        return false
    }

}
