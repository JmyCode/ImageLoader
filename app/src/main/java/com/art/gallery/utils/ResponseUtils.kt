package com.art.gallery.utils

import com.art.gallery.data.ImageModel
import com.art.gallery.data.Response

class ResponseUtils {

    companion object {

        @JvmStatic
        fun filterData(response: Response<ImageModel>): List<String> {
            val urls = mutableListOf<String>()
            response.results!!.forEach {
                val url = it.urls?.url
                if (url != null) {
                    urls.add(url)
                }
            }
            return urls
        }

    }
}
