package com.andyyang.eyepetizer.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.Observer
import com.andyyang.common.base.view.activity.BindActivity
import com.andyyang.eyepetizer.R
import com.andyyang.eyepetizer.databinding.ActivitySplashBinding
import com.andyyang.eyepetizer.ui.main.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by AndyYang.
 * data: 2018/3/4.
 * mail: AndyyYang2014@126.com.
 */

class SplashActivity : BindActivity<ActivitySplashBinding>() {

    override val contentLayoutId = R.layout.activity_splash

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(splashKodeinModule)
    }

    private val viewModel: SplashViewModel by instance()

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.animEv.observe(this, Observer {
            setAnimation()
        })
        binding.vm = viewModel
    }

    private fun setAnimation() {
        val scaleAnimation = ScaleAnimation(
            1.0f,
            1.2f,
            1.0f,
            1.2f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 2000
        binding.splashBg.startAnimation(scaleAnimation)
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        })
    }

}

