package com.whiz.seriousscience.serioussciencevideos.network

import com.whiz.seriousscience.serioussciencevideos.models.ListVideos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    //Get response with video's information
    @GET("convert")
    fun getDataFromApi(@Query("url") query: String): Call<ListVideos>
}