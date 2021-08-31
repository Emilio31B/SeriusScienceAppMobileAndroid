package com.whiz.seriousscience.serioussciencevideos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.whiz.seriousscience.serioussciencevideos.models.VideoInformation
import com.whiz.seriousscience.serioussciencevideos.network.url

class DescriptionActivity : AppCompatActivity() {
    lateinit var id_img: ImageView
    lateinit var date: TextView
    lateinit var title: TextView
    lateinit var author: TextView
    lateinit var butYoutube: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        id_img = findViewById(R.id.iv_desc_image)
        date = findViewById(R.id.tv_desc_date)
        title = findViewById(R.id.tv_desc_title)
        author = findViewById(R.id.tv_desc_author)
        butYoutube = findViewById(R.id.but_to_youtube)

        setDataDescription()
    }

    private fun getVideoDescription(): VideoInformation{
        val data = intent.getSerializableExtra("description") as VideoInformation
        return data
    }

    private fun setDataDescription(){
        val data = getVideoDescription()
        date.text = data.date_published
        title.text = data.title
        author.text = data.author.name

        val url = url().getURLSpecificImg(data.id_img)
        Picasso.get()
            .load(url)
            .into(id_img)

        butYoutube.setOnClickListener{ toYoutube(data.url)}
    }
    
    private fun toYoutube(url: String) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.google.android.youtube")
        startActivity(intent)
    }
}