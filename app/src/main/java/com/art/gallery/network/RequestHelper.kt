package com.art.gallery.network

import com.art.gallery.data.ImageModel
import com.art.gallery.data.Response
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RequestHelper(private val api: GalleryApi) {

    fun getImages(offset: Int = 0): Observable<Response<ImageModel>> {
        return api
            .getImages(offset)
            .subscribeOn(Schedulers.io())
    }

}