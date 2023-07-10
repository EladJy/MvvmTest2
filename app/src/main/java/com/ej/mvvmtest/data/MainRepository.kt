package com.ej.mvvmtest.data

import com.ej.mvvmtest.api.RetrofitService
import com.ej.mvvmtest.data.models.VideosResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: RetrofitService) {
    suspend fun getVideosByQuery(query: String): VideosResponse {
        return retrofitService.getVideosByQuery(query)
    }
}