package com.art.gallery.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.replaceFragment(targetId: Int, fragment: Fragment) {
    beginTransaction().apply {
        replace(targetId, fragment)
        commitNowAllowingStateLoss()
    }
}