package com.andyyang.eyepetizer.ui.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.andyyang.eyepetizer.interfaces.LifeCycle
import com.andyyang.eyepetizer.interfaces.OnLifeCycleListener

/**
 * Created by AndyYang
 * date:2018/2/13.
 * mail:andyyang2014@126.com
 */

open class BaseActivity : AppCompatActivity(), LifeCycle {

    private var exitDialog: AlertDialog? = null
    private var lifeCycleListener: OnLifeCycleListener? = null

    override fun setOnLifeCycleListener(lifeCycleListener: OnLifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (noActionBar()) {
            supportActionBar!!.hide()
        }

        if (noStatusBar()) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        activities.add(this)
    }


    override fun onStart() {
        super.onStart()
        lifeCycleListener?.let { lifeCycleListener!!.onStart() }
    }

    override fun onResume() {
        super.onResume()
        lifeCycleListener?.let { lifeCycleListener!!.onResume() }
    }

    override fun onPause() {
        super.onPause()
        lifeCycleListener?.let { lifeCycleListener!!.onPause() }
    }

    override fun onStop() {
        super.onStop()
        lifeCycleListener?.let { lifeCycleListener!!.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycleListener?.let { lifeCycleListener!!.onDestroy() }
        activities.remove(this)
    }

    fun finishActivityByName(vararg activityClasses: Class<out BaseActivity>) {
        for (activity in activities) {
            for (activityClass in activityClasses) {
                if (activity.javaClass == activityClass) {
                    activity.finish()
                }
            }
        }
    }

    fun finishAllActivityAbord(vararg activityClasses: Class<out BaseActivity>) {
        for (activity in activities) {
            for (activityClass in activityClasses) {
                if (activity.javaClass != activityClass) {
                    activity.finish()
                }
            }
        }
    }

    fun finishAllActivity() {
        for (activity in activities) {
            activity.finish()
        }
    }

    override fun onBackPressed() {
        if (activities.size == 1) {
            val builder = AlertDialog.Builder(this)
                    .setMessage("确认退出应用？")
                    .setPositiveButton("确认") { _, _ -> super@BaseActivity.onBackPressed() }
                    .setNegativeButton("取消") { _, _ ->
                        if (exitDialog != null) {
                            exitDialog!!.dismiss()
                        }
                    }
            builder.setCancelable(false)
            exitDialog = builder.show()
        } else {
            super.onBackPressed()
        }
    }

    protected open fun noStatusBar(): Boolean {
        return false
    }

    protected fun noActionBar(): Boolean {
        return false
    }

    companion object {

        private val activities = ArrayList<BaseActivity>()

        val currentActivity: BaseActivity
            get() = activities[activities.size - 1]

        fun getCurrentActivity(pager: Int = 0): BaseActivity {
            return activities[activities.size - pager - 1]
        }
    }


}
