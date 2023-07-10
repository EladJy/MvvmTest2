package com.ej.mvvmtest.api

import com.ej.mvvmtest.BuildConfig
import com.ej.mvvmtest.data.models.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/youtube/v3/search?part=snippet&type=video&maxResults=10&key=${BuildConfig.YOUTUBE_API_KEY}")
    suspend fun getVideosByQuery(@Query("q") query: String) : VideosResponse
}