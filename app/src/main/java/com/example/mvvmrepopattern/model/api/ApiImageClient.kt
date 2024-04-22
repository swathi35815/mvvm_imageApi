package com.example.mvvmrepopattern.model.api

import com.example.mvvmrepopattern.model.data.ImageData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiImageInterface{
    @GET("photos")
    fun getData(): Call<ArrayList<ImageData>>
}

class ApiImageClient {
    companion object{
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiImageInterface::class.java)
    }
}