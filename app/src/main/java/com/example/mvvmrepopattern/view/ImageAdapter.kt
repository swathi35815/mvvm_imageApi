package com.example.mvvmrepopattern.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrepopattern.databinding.ItemLayoutBinding
import com.example.mvvmrepopattern.model.data.ImageData
import com.squareup.picasso.Picasso

class ImageAdapter(private val imageDataList : ArrayList<ImageData>?) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun myBindData(imageData : ImageData) {
            binding.textViewImageDataID.text = imageData.id.toString()
            binding.textViewImageDataTitle.text = imageData.title
            binding.textViewImageDataURL.text = imageData.url
            Picasso.with(itemView.context)
                .load(imageData.url)
                .into(binding.imageViewImageData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        imageDataList?.get(position)?.let { holder.myBindData(it) }
    }

    fun newImageData(newImageDataList : ArrayList<ImageData>) {
        imageDataList?.clear()
        imageDataList?.addAll(newImageDataList)
        notifyDataSetChanged()
    }
}