package com.andyyang.network.di

import com.andyyang.network.BuildConfig
import com.andyyang.network.api.GankApi
import com.andyyang.network.api.KaiyanApi
import com.andyyang.network.api.ServiceManager
import com.andyyang.network.utils.create
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

private const val SERVICE_MODULE_TAG = "serviceModule"

val serviceModule = Kodein.Module(SERVICE_MODULE_TAG) {

    bind<GankApi>() with singleton {
        instance<Retrofit>().create<GankApi>(BuildConfig.GANK_BASE_URL)
    }

    bind<KaiyanApi>() with singleton {
        instance<Retrofit>().create<KaiyanApi>(BuildConfig.KAIYAN_BASE_URL)
    }

    bind<ServiceManager>() with singleton {
        ServiceManager(instance(),instance())
    }

}