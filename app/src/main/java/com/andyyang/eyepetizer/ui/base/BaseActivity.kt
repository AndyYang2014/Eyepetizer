package com.andyyang.eyepetizer.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.andyyang.eyepetizer.interfaces.LifeCycle
import com.andyyang.eyepetizer.interfaces.OnLifeCycleListener

/**
 * Created by AndyYang
 * date:2018/2/13.
 * mail:andyyang2014@126.com
 */

abstract class BaseActivity : AppCompatActivity(), LifeCycle {

    private val activities = ArrayList<BaseActivity>()
    private var lifeCycleListener: OnLifeCycleListener? = null

    override fun setOnLifeCycleListener(lifeCycleListener: OnLifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayoutId())

        if (noActionBar()) {
            supportActionBar!!.hide()
        }

        if (noStatusBar()) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        initActivity(savedInstanceState)
        activities.add(this)
    }

    abstract fun initActivity(savedInstanceState: Bundle?)

    abstract fun getActivityLayoutId(): Int

    override fun onStart() {
        super.onStart()
        lifeCycleListener?.onStart()
    }

    override fun onResume() {
        super.onResume()
        lifeCycleListener?.onResume()
    }

    override fun onPause() {
        super.onPause()
        lifeCycleListener?.onPause()
    }

    override fun onStop() {
        super.onStop()
        lifeCycleListener?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycleListener?.onDestroy()
        activities.remove(this)
    }

    fun finishActivityByName(vararg activityClasses: Class<out BaseActivity>) {
        for (activity in activities) {
            activityClasses
                    .filter { activity.javaClass == it }
                    .forEach { activity.finish() }
        }
    }

    fun finishAllActivityAbord(vararg activityClasses: Class<out BaseActivity>) {
        activities.forEach { activity ->
            activityClasses.filter { activity.javaClass != it }
                    .forEach { activity.finish() }
        }
    }

    fun getCurrentActivity(pager: Int = 0): BaseActivity {
        return activities[activities.size - pager - 1]
    }

    fun finishAllActivity() {
        activities.forEach {
            it.finish()
        }
    }

    protected open fun noStatusBar(): Boolean {
        return false
    }

    protected fun noActionBar(): Boolean {
        return false
    }

}
