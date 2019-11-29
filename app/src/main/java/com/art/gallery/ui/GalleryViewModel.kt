package com.art.gallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.art.gallery.data.Data
import com.art.gallery.data.NoInternetConnectionException
import com.art.gallery.di.DaggerUtils
import com.art.gallery.network.RequestHelper
import com.art.gallery.network.connectivity.NetworkUtils
import com.art.gallery.utils.ResponseUtils.Companion.filterData
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GalleryViewModel : ViewModel() {

    @Inject
    lateinit var requestHelper: RequestHelper

    private lateinit var disposable: Disposable
    private var urlsData: MutableLiveData<Data> = MutableLiveData()
    val NO_INTERNET_CONNECTION = Data(error = NoInternetConnectionException())

    init {
        DaggerUtils.getAppComponent().inject(this)
        obtainImages(0)
    }

    fun obtainImages(page: Int) {
        if (!NetworkUtils.isConnected()) {
            notifyData(NO_INTERNET_CONNECTION)
            return
        }

        disposable = requestHelper
            .getImages(page)
            .map(::filterData)
            .map { Data(images = it) }
            .onErrorReturn { NO_INTERNET_CONNECTION }
            .subscribe(::notifyData, { errorMessage() })
    }


    fun getUrls(): LiveData<Data> {
        return urlsData
    }

    private fun errorMessage() {}

    fun loadMore(page: Int) {
        obtainImages(page)
    }

    fun refreshArt() {
        obtainImages(0)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    private fun notifyData(data: Data) {
        urlsData.postValue(data)
    }
}