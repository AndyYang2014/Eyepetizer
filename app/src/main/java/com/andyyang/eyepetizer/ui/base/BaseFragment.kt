package com.andyyang.eyepetizer.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.andyyang.eyepetizer.interfaces.LifeCycle
import com.andyyang.eyepetizer.interfaces.OnLifeCycleListener
import com.andyyang.eyepetizer.utils.Logger

/**
 * Created by AndyYang
 * date:2018/2/13.
 * mail:andyyang2014@126.com
 */
abstract class BaseFragment : Fragment(), LifeCycle {
    protected var bundle: Bundle? = null
    private var lifeCycleListener: OnLifeCycleListener? = null
    lateinit protected var activity: BaseActivity
    protected lateinit var rootView: View

    override fun setOnLifeCycleListener(lifeCycleListener: OnLifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = View.inflate(this.context, getFragmentLayoutId(), null)
        bundle = savedInstanceState
        if (noActionBar()) {
            activity.supportActionBar!!.hide()
        }

        if (noStatusBar()) {
            activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        initFragment(rootView, savedInstanceState)
        return rootView
    }

    protected abstract fun initFragment(view: View, savedInstanceState: Bundle?)

    protected abstract fun getFragmentLayoutId(): Int


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    override fun onStart() {
        super.onStart()
        lifeCycleListener?.let { lifeCycleListener!!.onStart() }
        Logger.d(this.javaClass.simpleName + " onStart")
    }

    override fun onResume() {
        super.onResume()
        lifeCycleListener?.let { lifeCycleListener!!.onResume() }
        Logger.d(this.javaClass.simpleName + " onResume")
    }

    override fun onPause() {
        super.onPause()
        lifeCycleListener?.let { lifeCycleListener!!.onPause() }
        Logger.d(this.javaClass.simpleName + " onPause")
    }

    override fun onStop() {
        super.onStop()
        lifeCycleListener?.let { lifeCycleListener!!.onStop() }
        Logger.d(this.javaClass.simpleName + " onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycleListener?.let { lifeCycleListener!!.onDestroy() }
        Logger.d(this.javaClass.simpleName + " onDestory")
    }


    fun finishActivityByName(vararg activityClasses: Class<out BaseActivity>) {
        activity.finishActivityByName(*activityClasses)
    }

    fun finishAllActivityAbord(vararg activityClasses: Class<out BaseActivity>) {
        activity.finishAllActivityAbord(*activityClasses)
    }

    fun finishAllActivity() {
        activity.finishAllActivity()
    }

    protected fun noStatusBar(): Boolean {
        return false
    }

    protected fun noActionBar(): Boolean {
        return false
    }

    open fun setupToolbar(): Boolean {
//        if (tabId != currentFragment) {//解决mainactivity fragment切换时，toolbar更新bug（homefragment中recyclerview滚动会更新toolbar，如果不控制，在滚动过程中切换了tab，toolbar会依旧被更新）
        return true
//        }
//        return false
    }


}
