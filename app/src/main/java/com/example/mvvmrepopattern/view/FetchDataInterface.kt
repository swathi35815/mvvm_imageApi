package com.example.mvvmrepopattern.view

import androidx.lifecycle.LiveData
import com.example.mvvmrepopattern.model.data.ImageData

interface FetchDataInterface {
    fun onFetching()
    fun onSuccess(success : LiveData<ArrayList<ImageData>>)
    fun onFailure(message : String)
}