package com.whiz.seriousscience.serioussciencevideos

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whiz.seriousscience.serioussciencevideos.Adapter.RecyclerViewAdapter
import com.whiz.seriousscience.serioussciencevideos.models.ListVideos
import com.whiz.seriousscience.serioussciencevideos.models.VideoInformation
import com.whiz.seriousscience.serioussciencevideos.network.RetrofitInstance
import com.whiz.seriousscience.serioussciencevideos.network.RetrofitService
import com.whiz.seriousscience.serioussciencevideos.network.url
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var videoRecyclerView: RecyclerView
    lateinit var items: List<VideoInformation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecyclerView = findViewById(R.id.recyclerView)


        val retroInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retroInstance.getDataFromApi(url().getURLCode()).enqueue(object : Callback<ListVideos> {

            override fun onResponse(call: Call<ListVideos>, response: Response<ListVideos>) {

                items = response.body()!!.items
                for(i in items){
                    i.id_img = Uri.parse(i.url).getQueryParameter("v").toString()
                }
                showData(items)
            }
            override fun onFailure(call: Call<ListVideos>, t: Throwable) {
                Toast.makeText(getApplicationContext(), "No se pudo cargar la información",Toast.LENGTH_SHORT).show()
            }

        })


    }
    private fun showData(items: List<VideoInformation>){
        videoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerViewAdapter(items)
        }
    }
    

}