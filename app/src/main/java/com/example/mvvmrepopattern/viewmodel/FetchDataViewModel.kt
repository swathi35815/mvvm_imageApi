package com.example.mvvmrepopattern.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmrepopattern.model.repository.ImageRepository
import com.example.mvvmrepopattern.view.FetchDataInterface

class FetchDataViewModel : ViewModel() {

    var fetchDataListener : FetchDataInterface? = null

    fun onFetchDataButtonClicked(view : View) {
        fetchDataListener?.onFetching()

        val imageDataLiveData = ImageRepository().getData()

        // Observe the LiveData object returned by ImageRepository
        imageDataLiveData.observeForever { imageData ->
            if (imageData != null && imageData.isNotEmpty()) {
                // If data is available, pass the LiveData object to onSuccess
                fetchDataListener?.onSuccess(imageDataLiveData)
            } else {
                // If data is not available, handle failure
                fetchDataListener?.onFailure("Failed to fetch data from server")
            }
        }
    }
}