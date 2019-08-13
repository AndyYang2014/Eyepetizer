package com.andyyang.eyepetizer.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyyang.common.base.viewmodel.BaseViewModel
import com.andyyang.common.utils.io_main
import com.uber.autodispose.autoDisposable

@SuppressWarnings("checkResult")
class SplashViewModel(
    private val repo: SplashRepository
) : BaseViewModel() {

    init {
        repo.randomGirl()
            .io_main()
            .autoDisposable(this)
            .subscribe()
    }


}

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private val repo: SplashRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SplashViewModel(repo) as T
}