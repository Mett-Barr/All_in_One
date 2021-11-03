package com.example.allinone.page2.internet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.allinone.databinding.MarsPhotoItemBinding
import com.example.allinone.page2.internet.network.MarsPhoto

class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.ViewHolder>(DiffCallBack) {
    class ViewHolder(
        private var binding: MarsPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: MarsPhoto) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.imgSrcUrl == newItem.imgSrcUrl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(MarsPhotoItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}