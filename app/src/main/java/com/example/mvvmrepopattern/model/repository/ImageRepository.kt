package com.example.mvvmrepopattern.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmrepopattern.model.api.ApiImageClient
import com.example.mvvmrepopattern.model.data.ImageData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository() {
    fun getData(): LiveData<ArrayList<ImageData>> {
        val imageResponse = MutableLiveData<ArrayList<ImageData>>()

        // Fetch data from API
        ApiImageClient.retrofitBuilder.getData()
            .enqueue(object : Callback<ArrayList<ImageData>> {
                override fun onResponse(
                    call: Call<ArrayList<ImageData>>,
                    response: Response<ArrayList<ImageData>>
                ) {
                    if (response.isSuccessful) {
                        imageResponse.postValue(response.body())
                        Log.i("myTag", "Response success: ${response.body().toString()}")
                    } else {
                        Log.i("myTag", "Response Failure: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ImageData>>, t: Throwable) {
                    Log.i("myTag", "Failure: ${t.toString()}")
                }
            })

        return imageResponse
    }
}