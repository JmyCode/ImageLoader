package com.art.gallery.network

import com.art.gallery.BuildConfig
import okhttp3.Interceptor
import java.io.IOException

class HeadersProvider : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
        val builder = chain.request().newBuilder()
        val clientId = "Client-ID " + BuildConfig.CLIENT_ID

        builder.header("Authorization", "  $clientId")

        return chain.proceed(builder.build())
    }
}