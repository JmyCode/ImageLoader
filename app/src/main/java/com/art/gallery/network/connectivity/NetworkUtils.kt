package com.art.gallery.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import com.art.gallery.GalleryApplication

object NetworkUtils {

    enum class ConnectivityType {
        NONE,
        MOBILE,
        WIFI,
        OTHER
    }

    private var lastCheckConnectionTime: Long = 0
    private var lastConnection = false

    fun isConnected(): Boolean {
        if ((System.currentTimeMillis() - lastCheckConnectionTime) > 500) {
            lastConnection = isConnectionNow()
            lastCheckConnectionTime = System.currentTimeMillis()
        }

        return lastConnection
    }

    fun isConnectionNow(): Boolean {
        return getConnectivityType() !== ConnectivityType.NONE
    }

    private fun getConnectivityType(): ConnectivityType {
        val networkInfo = getConnectivityManager().activeNetworkInfo
        if (networkInfo === null || !networkInfo.isConnected) {
            return ConnectivityType.NONE
        }

        return when (networkInfo.type) {
            ConnectivityManager.TYPE_MOBILE -> ConnectivityType.MOBILE
            ConnectivityManager.TYPE_WIFI -> ConnectivityType.WIFI
            else -> ConnectivityType.OTHER
        }
    }

    private fun getConnectivityManager(): ConnectivityManager {
        return GalleryApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}