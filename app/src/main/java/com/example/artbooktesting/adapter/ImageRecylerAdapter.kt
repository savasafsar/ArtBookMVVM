package com.example.artbooktesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.roomdb.Art
import javax.inject.Inject

class ImageRecylerAdapter @Inject constructor(
    val glide  :RequestManager
)  : RecyclerView.Adapter<ImageRecylerAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    private var onItemClickListener : ((String)->Unit)? = null
    private val diffUtil = object  : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem== newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem== newItem
        }

    }

    private val recylerListDiffer = AsyncListDiffer(this,diffUtil)
    var images : List<String>
        get() = recylerListDiffer.currentList
        set(value) = recylerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row,parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setOnItemClickListener(listener:(String)->Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
              val imageView = holder.itemView.findViewById<ImageView>(R.id.singleArtImageView)
        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }

    }
}