package com.whiz.seriousscience.serioussciencevideos.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.whiz.seriousscience.serioussciencevideos.DescriptionActivity
import com.whiz.seriousscience.serioussciencevideos.models.VideoInformation
import com.whiz.seriousscience.serioussciencevideos.R
import com.whiz.seriousscience.serioussciencevideos.network.url


class RecyclerViewAdapter(val items: List<VideoInformation>) : RecyclerView.Adapter<RecyclerViewAdapter.VideoViewHolder>() {
    //var items = items
    //var items = ArrayList<VideoInformation>()

    /*fun setUpdatedData(items : ArrayList<VideoInformation>) {
        this.items = items
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_image)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val author = itemView.findViewById<TextView>(R.id.tv_author)

        fun bind(data : VideoInformation) {
            title.text = data.title
            author.text = data.author.name

            val url = url().getURLSpecificImg(data.id_img)
            Picasso.get()
                .load(url)
                .into(image)

            itemView.setOnClickListener{
                var intent = Intent(itemView.context,DescriptionActivity::class.java)
                var video_desc = VideoInformation(data.title, data.author,
                    data.date_published,
                    data.url,
                    data.id_img)
                intent.putExtra("description",video_desc)
                itemView.context.startActivity(intent)
            }
        }
    }

}