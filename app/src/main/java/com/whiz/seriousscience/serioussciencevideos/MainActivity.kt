package com.whiz.seriousscience.serioussciencevideos

import android.animation.ObjectAnimator
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
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
import android.content.Intent




class MainActivity : AppCompatActivity() {

    private lateinit var videoRecyclerView: RecyclerView
    var items: List<VideoInformation>? = null
    lateinit var progressBar: ProgressBar
    lateinit var tv_no_data: TextView
    lateinit var but_update: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressbar)
        tv_no_data = findViewById(R.id.tv_no_data)
        but_update = findViewById(R.id.but_update)

        getInfoWithRetrofit(items,progressBar,tv_no_data,but_update)
    }



    //Show info of each video in recyclerview - Set Adapter
    private fun showData(items: List<VideoInformation>,progressBar: ProgressBar){
        videoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            hasFixedSize()
            adapter = RecyclerViewAdapter(items)
            visibility = View.VISIBLE
        }
        progressBar.visibility = View.GONE
    }


    //Get list of video's description with retrofit
    private fun getInfoWithRetrofit(items: List<VideoInformation>?,progressBar: ProgressBar,tv_no_data: TextView,but_update: Button){
        var items_entry = items
        val retroInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retroInstance.getDataFromApi(url().getURLCode()).enqueue(object : Callback<ListVideos> {

            override fun onResponse(call: Call<ListVideos>, response: Response<ListVideos>) {

                if(response.body()?.items?.isNotEmpty() == true) {
                    items_entry = response.body()?.items
                }
                if(!items_entry.isNullOrEmpty()){
                    for(i in items_entry as ArrayList<VideoInformation>){
                        i.id_img = Uri.parse(i.url).getQueryParameter("v").toString()
                    }
                    showData(items_entry as ArrayList<VideoInformation>,progressBar)
                }
            }
            override fun onFailure(call: Call<ListVideos>, t: Throwable) {
                progressBar.visibility = View.GONE
                tv_no_data.visibility = View.VISIBLE
                but_update.visibility = View.VISIBLE
                Toast.makeText(getApplicationContext(), "No se pudo cargar la información",Toast.LENGTH_SHORT).show()
                but_update.setOnClickListener { updateData() }
            }

        })
    }

    //Restart activity to load Data
    private fun updateData() {
        finish()
        startActivity(getIntent())
    }

}