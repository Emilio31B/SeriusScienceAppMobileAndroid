package com.whiz.seriousscience.serioussciencevideos.models

import android.net.Uri
import java.io.Serializable

//DATA CLASS with same name of each field from video information

data class ListVideos(val items: ArrayList<VideoInformation>)
data class VideoInformation(
    val title: String,
    val author: Author,
    val date_published: String,
    val url: String,
    var id_img: String ): Serializable

//Author field has a name field
data class Author(val name: String): Serializable