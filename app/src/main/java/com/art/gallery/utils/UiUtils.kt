package com.art.gallery.utils

import android.view.View

class UiUtils {
    companion object {

        fun show(vararg views: View) {
            for (view in views) {
                view.visibility = View.VISIBLE
            }
        }

        fun hide(vararg views: View) {
            for (view in views) {
                view.visibility = View.GONE
            }
        }
    }
}