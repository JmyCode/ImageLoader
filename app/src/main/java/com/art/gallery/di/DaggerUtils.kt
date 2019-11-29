package com.art.gallery.di

import com.art.gallery.GalleryApplication
import com.art.gallery.di.components.ApplicationComponent

class DaggerUtils {

    companion object {
        fun getAppComponent(): ApplicationComponent  {
            return GalleryApplication.appComponent
        }
    }
}