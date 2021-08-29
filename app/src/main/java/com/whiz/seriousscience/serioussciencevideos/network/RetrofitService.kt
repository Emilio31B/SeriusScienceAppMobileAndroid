package com.whiz.seriousscience.serioussciencevideos.network

import com.whiz.seriousscience.serioussciencevideos.models.ListVideos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    @GET("convert")
    //suspend fun getDataFromApi(@Query("url") query: String): ListVideos
    fun getDataFromApi(@Query("url") query: String): Call<ListVideos>
}