package com.art.gallery.ui.view.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.art.gallery.R
import com.art.gallery.utils.ImageStore

class ArtViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val cover = view.findViewById<ImageView>(R.id.cover)

    fun bind(url: String) {
        ImageStore
            .with(cover.context)
            .load(url, cover)
    }

}