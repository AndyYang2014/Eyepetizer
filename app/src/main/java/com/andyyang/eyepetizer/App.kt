package com.andyyang.eyepetizer

import android.content.Context
import com.andyyang.common.base.BaseApp
import com.andyyang.network.di.httpClientModule
import com.andyyang.network.di.serviceModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import zlc.season.rxdownload3.RxDownload
import zlc.season.rxdownload3.core.DownloadConfig

/**
 * Created by liuyang on 2019/8/13.
 */
class App : BaseApp(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<Context>() with singleton { this@App }
        import(androidModule(this@App))
        import(androidXModule(this@App))
        import(serviceModule)
        import(httpClientModule)
    }

    override fun onCreate() {
        super.onCreate()
        initRxDownload()
    }

    private fun initRxDownload() {
        val builder = DownloadConfig.Builder.create(this)
            .enableAutoStart(true)
            .enableDb(true)

        DownloadConfig.init(builder)
    }
}