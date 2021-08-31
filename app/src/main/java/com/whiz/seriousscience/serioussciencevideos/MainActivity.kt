package com.whiz.seriousscience.serioussciencevideos

import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.whiz.seriousscience.serioussciencevideos.viewModel.MainViewModel
import com.whiz.seriousscience.serioussciencevideos.network.url
import java.lang.Exception
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity() {

    private lateinit var videoRecyclerView: RecyclerView
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

        getInfoFromViewModel(progressBar,tv_no_data,but_update)
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

    ////Get list of video's description with retrofit
    private fun getInfoFromViewModel(progressBar: ProgressBar,tv_no_data: TextView,but_update: Button) {
        var items_entry: List<VideoInformation>
        var mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getVideoListObserver().observe(this, {
            if (it != null && it.items.isNotEmpty()) {
                items_entry = it.items
                for (i in items_entry as ArrayList<VideoInformation>) {
                    i.id_img = Uri.parse(i.url).getQueryParameter("v").toString()
                }
                showData(items_entry as ArrayList<VideoInformation>, progressBar)
            } else {
                progressBar.visibility = View.GONE
                tv_no_data.visibility = View.VISIBLE
                but_update.visibility = View.VISIBLE
                Toast.makeText(
                    getApplicationContext(),
                    "No se pudo cargar la información",
                    Toast.LENGTH_SHORT
                ).show()
                but_update.setOnClickListener { updateData() }
            }
        })
        mainViewModel.makeApiCall()
    }

    //Restart activity to load Data
    private fun updateData() {
        finish()
        startActivity(getIntent())
    }


}