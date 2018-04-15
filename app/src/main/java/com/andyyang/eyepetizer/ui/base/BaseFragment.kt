package com.andyyang.eyepetizer.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andyyang.eyepetizer.interfaces.LifeCycle
import com.andyyang.eyepetizer.interfaces.OnLifeCycleListener

/**
 * Created by AndyYang
 * date:2018/2/13.
 * mail:andyyang2014@126.com
 */
abstract class BaseFragment : Fragment(), LifeCycle {
    private var lifeCycleListener: OnLifeCycleListener? = null
    lateinit protected var mActivity: BaseActivity

    override fun setOnLifeCycleListener(lifeCycleListener: OnLifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            View.inflate(mActivity, getFragmentLayoutId(), null)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFragment(savedInstanceState)
    }

    protected abstract fun initFragment(savedInstanceState: Bundle?)

    protected abstract fun getFragmentLayoutId(): Int


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
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
    }


    fun finishActivityByName(vararg activityClasses: Class<out BaseActivity>) {
        mActivity.finishActivityByName(*activityClasses)
    }

    fun finishAllActivityAbord(vararg activityClasses: Class<out BaseActivity>) {
        mActivity.finishAllActivityAbord(*activityClasses)
    }

    fun finishAllActivity() {
        mActivity.finishAllActivity()
    }

    open fun setupToolbar(): Boolean {
//        if (tabId != currentFragment) {//解决mainactivity fragment切换时，toolbar更新bug（homefragment中recyclerview滚动会更新toolbar，如果不控制，在滚动过程中切换了tab，toolbar会依旧被更新）
        return true
//        }
//        return false
    }


}
