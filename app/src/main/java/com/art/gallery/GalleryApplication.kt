package com.art.gallery

import android.app.Application
import com.art.gallery.di.components.ApplicationComponent

class GalleryApplication : Application() {

    companion object {
        lateinit var application: GalleryApplication
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildDagger()
        application = this
    }

    private fun buildDagger() {
        appComponent = ApplicationComponent.Builder.build(this)
    }
}