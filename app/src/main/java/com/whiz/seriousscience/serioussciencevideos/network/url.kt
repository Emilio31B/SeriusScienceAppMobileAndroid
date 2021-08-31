package com.whiz.seriousscience.serioussciencevideos.network

class url() {

    //absolute url
    private val url: String = "https://feed2json.org/"

    //Link parameter
    private val url_code: String = "https://www.youtube.com/feeds/videos.xml?channel_id=UCComKOHir2WrDuRZXP8DT-A"

    //base url to get preview image from video
    private val url_specific_img= "https://img.youtube.com/vi/"

    //last parameter to get preview image
    private val end_of_url = "/mqdefault.jpg"

    //get absolute url
    fun getURL():String {
        return url
    }

    //Get preview image from video with its code
    fun getURLSpecificImg(id: String): String {
        return url_specific_img + id + end_of_url
    }

    //return parameter url_code
    fun getURLCode(): String {
        return url_code
    }
}