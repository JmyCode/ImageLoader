package com.art.gallery.network

import com.art.gallery.data.ImageModel
import com.art.gallery.data.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {

    @GET("search/photos?&query=car&per_page=20")
    fun getImages(@Query("page") page: Int): Observable<Response<ImageModel>>

}