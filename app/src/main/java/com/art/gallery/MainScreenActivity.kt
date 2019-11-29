package com.art.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.art.gallery.ui.view.GalleryFragment
import com.art.gallery.utils.replaceFragment

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.replaceFragment(R.id.content, GalleryFragment.create())
    }
}
