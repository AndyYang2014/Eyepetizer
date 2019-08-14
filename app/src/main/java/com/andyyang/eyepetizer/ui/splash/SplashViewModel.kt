package com.andyyang.eyepetizer.ui.splash

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyyang.common.base.BaseApp
import com.andyyang.common.base.viewmodel.BaseViewModel
import com.andyyang.common.utils.SingleLiveEvent
import com.andyyang.common.utils.io_main
import com.andyyang.eyepetizer.R
import com.uber.autodispose.autoDisposable

class SplashViewModel(
    val repo: SplashRepository
) : BaseViewModel() {

    val animEv = SingleLiveEvent<Void>()
    val bgDrawable = ObservableField<Drawable>()

    init {
        repo.getRandomGirl()
            .io_main()
            .autoDisposable(this)
            .subscribe()

        repo.getDefaultBg(
            onInit = {
                val bitmap = BaseApp.INSTANCE.resources.getDrawable(R.drawable.splash_default)
                bgDrawable.set(bitmap)
                animEv.call()
            },
            onNext = {
                bgDrawable.set(BitmapDrawable(BaseApp.INSTANCE.resources, it))
                animEv.call()
            })
    }

}

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private val repo: SplashRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SplashViewModel(repo) as T
}