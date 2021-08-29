package com.whiz.seriousscience.serioussciencevideos.models

import android.net.Uri
import java.io.Serializable


data class ListVideos(val items: ArrayList<VideoInformation>)
data class VideoInformation(
    val title: String,
    val author: Author,
    val date_published: String,
    val url: String,
    var id_img: String ): Serializable

data class Author(val name: String): Serializable