package com.whiz.seriousscience.serioussciencevideos.network

class url() {
    private val url: String = "https://feed2json.org/"
    private val url_code: String = "https://www.youtube.com/feeds/videos.xml?channel_id=UCComKOHir2WrDuRZXP8DT-A"
    private val url_specific_img= "https://img.youtube.com/vi/"
    private val end_of_url = "/mqdefault.jpg"

    fun getURL():String {
        return url;
    }

    fun getURLSpecificImg(id: String): String {
        return url_specific_img + id + end_of_url;
    }

    fun getURLCode(): String {
        return url_code
    }
}