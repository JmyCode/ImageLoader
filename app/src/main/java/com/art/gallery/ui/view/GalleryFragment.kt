package com.art.gallery.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.art.gallery.R
import com.art.gallery.data.Data
import com.art.gallery.data.NoInternetConnectionException
import com.art.gallery.network.connectivity.NetworkUtils
import com.art.gallery.ui.GalleryViewModel
import com.art.gallery.utils.UiUtils
import com.google.android.material.snackbar.Snackbar


class GalleryFragment : Fragment() {

    private val columnsCount = 2

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.refreshLayout)
    lateinit var refreshLayout: SwipeRefreshLayout
    @BindView(R.id.progress)
    lateinit var progressView: ProgressBar

    companion object {
        fun create() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel
    private lateinit var artAdapter: ArtAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)

        artAdapter = ArtAdapter(::loadMore)

        val gridLayoutManager = GridLayoutManager(context, columnsCount)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (artAdapter.getItemViewType(position) == ViewType.LOADER) 2 else 1
            }
        }

        recyclerView.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)

            adapter = artAdapter
        }

        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        viewModel.getUrls().observe(this, Observer { setPictures(it) })

        refreshLayout.setOnRefreshListener(::onRefresh)

    }

    private fun setPictures(data: Data) {
        if (data.error is NoInternetConnectionException) {
            showSnackBar()
            return
        }

        artAdapter.populate(data.images)
        hideLoader()
        showPictures()
    }

    private fun onRefresh() {
        if (NetworkUtils.isConnected()) {
            artAdapter.clearItems()
            viewModel.refreshArt()
        } else {
            stopRefresh()
        }
    }

    private fun loadMore(page: Int) {
        if (!NetworkUtils.isConnected()) {
            showSnackBar()
            return
        }
        viewModel.loadMore(page)
    }

    private fun stopRefresh() {
        if (refreshLayout.isRefreshing) {
            refreshLayout.isRefreshing = false
        }
    }

    private fun hideLoader() {
        stopRefresh()
        UiUtils.hide(progressView)
    }

    private fun showPictures() {
        UiUtils.show(recyclerView)
    }

    private fun showSnackBar() {
        val snakbar =
            Snackbar.make(recyclerView, R.string.check_network, Snackbar.LENGTH_INDEFINITE)
        snakbar.setAction(R.string.update) { v ->
            viewModel.obtainImages(0)
            snakbar.dismiss()
        }.show()
    }

}