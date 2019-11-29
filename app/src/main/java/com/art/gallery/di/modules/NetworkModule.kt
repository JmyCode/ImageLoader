package com.art.gallery.di.modules

import com.art.gallery.BuildConfig
import com.art.gallery.di.scope.ApplicationScope
import com.art.gallery.network.BASE_URL
import com.art.gallery.network.GalleryApi
import com.art.gallery.network.HeadersProvider
import com.art.gallery.network.RequestHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun retrofit(): Retrofit {

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addNetworkInterceptor(HeadersProvider())

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(interceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRequestHelper(retrofit: Retrofit): RequestHelper {
        return RequestHelper(retrofit.create(GalleryApi::class.java))
    }


}