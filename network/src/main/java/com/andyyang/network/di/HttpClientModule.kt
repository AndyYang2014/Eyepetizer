package com.andyyang.network.di

import com.andyyang.network.BuildConfig
import com.andyyang.network.utils.ssl.TrustAllHostnameVerifier
import com.andyyang.network.utils.ssl.TrustAllManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory

private const val HTTP_CLIENT_MODULE_TAG = "httpClientModule"
const val HTTP_CLIENT_MODULE_INTERCEPTOR_LOG_TAG = "http_client_module_interceptor_log_tag"

const val CONNECT_TIME_OUT_SECONDS = 10
const val READ_TIME_OUT_SECONDS = 30
const val BASE_URL = "https://www.weibopay.com"

val httpClientModule = Kodein.Module(HTTP_CLIENT_MODULE_TAG) {

    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .client(instance())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    bind<Interceptor>(HTTP_CLIENT_MODULE_INTERCEPTOR_LOG_TAG) with singleton {
        HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    bind<SSLSocketFactory>() with singleton {
        TrustAllManager.createSSLSocketFactory()
    }

    bind<TrustAllHostnameVerifier>() with singleton {
        TrustAllHostnameVerifier()
    }

    bind<TrustAllManager>() with singleton {
        TrustAllManager()
    }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .connectTimeout(
                CONNECT_TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                READ_TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(instance(HTTP_CLIENT_MODULE_INTERCEPTOR_LOG_TAG))
            .sslSocketFactory(instance(), instance())
            .hostnameVerifier(instance())
            .build()
    }
}