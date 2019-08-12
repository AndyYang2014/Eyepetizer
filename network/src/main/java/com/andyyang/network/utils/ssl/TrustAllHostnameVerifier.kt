package com.andyyang.network.utils.ssl

import android.annotation.SuppressLint
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 *Created by liuyang on 2019/7/26.
 */
class TrustAllHostnameVerifier : HostnameVerifier {
    @SuppressLint("BadHostnameVerifier")
    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        return true
    }
}