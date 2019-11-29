package com.art.gallery.di.components

import android.app.Application
import com.art.gallery.di.modules.NetworkModule
import com.art.gallery.di.scope.ApplicationScope
import com.art.gallery.ui.GalleryViewModel
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(galleryViewModel: GalleryViewModel)

    class Builder {
        companion object {
            fun build(application: Application): ApplicationComponent {
                return DaggerApplicationComponent.builder()
                    .build()
            }
        }
    }
}