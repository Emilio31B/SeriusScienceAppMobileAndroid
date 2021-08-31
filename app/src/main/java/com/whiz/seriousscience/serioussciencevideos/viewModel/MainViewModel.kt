package com.whiz.seriousscience.serioussciencevideos.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiz.seriousscience.serioussciencevideos.models.ListVideos
import com.whiz.seriousscience.serioussciencevideos.network.RetrofitInstance
import com.whiz.seriousscience.serioussciencevideos.network.RetrofitService
import com.whiz.seriousscience.serioussciencevideos.network.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    var videoListLiveData : MutableLiveData<ListVideos>

    init {
        videoListLiveData = MutableLiveData()
    }

    // Return list videos
    fun getVideoListObserver(): MutableLiveData<ListVideos> {
        return videoListLiveData
    }

    // Get response from API
    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            retroInstance.getDataFromApi(url().getURLCode()).enqueue(object : Callback<ListVideos> {
                override fun onResponse(call: Call<ListVideos>, response: Response<ListVideos>) {
                    videoListLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<ListVideos>, t: Throwable) {

                }

            })

        }
    }
}