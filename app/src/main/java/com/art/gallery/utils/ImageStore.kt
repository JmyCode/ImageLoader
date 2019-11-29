package com.art.gallery.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImageStore private constructor(context: Context) {

    companion object {
        fun with(context: Context): ImageStore {
            return ImageStore(context)
        }

        fun requestOption(): RequestOptions {
            return RequestOptions()
                .placeholder(android.R.drawable.ic_media_play)
        }
    }

    private var requestManager: RequestManager = Glide.with(context)

    fun load(url: String, view: ImageView) {
        requestManager
            .load(url)
            .apply(requestOption())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

}
