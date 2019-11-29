package com.art.gallery.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art.gallery.R
import com.art.gallery.ui.view.holders.ArtViewHolder
import com.art.gallery.ui.view.holders.LoaderViewHolder


class ArtAdapter(val loadMore: (nextPage: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemPerPage = 20
        var urls = mutableListOf<String>()

    private var isLastData = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_layout, parent, false)
        return when (viewType) {
            ViewType.ART -> ArtViewHolder(view)
            ViewType.LOADER -> LoaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.bottom_progress_bar_layout,
                    parent, false
                )
            )
            else -> throw RuntimeException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != ViewType.LOADER && holder is ArtViewHolder) {
            holder.bind(urls[position])
        } else {
            loadMore.invoke(urls.size)
        }
    }

    override fun getItemCount(): Int {
        return if (urls.isNotEmpty() && !isLastData) urls.size + 1 else urls.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > urls.size - 1) ViewType.LOADER else ViewType.ART
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun populate(coverPath: List<String>) {
        urls.addAll(coverPath)
        isLastData = urls.size < itemPerPage
        if (urls.size == coverPath.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(urls.size - coverPath.size, coverPath.size)
        }
    }

    fun clearItems() {
        urls.clear()
        notifyDataSetChanged()
    }
}

class ViewType {
    companion object {
        const val LOADER = 0
        const val ART = 1
    }

}