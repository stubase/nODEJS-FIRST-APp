package com.capstone.hibykes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.databinding.ItemStationsBinding

class StationAdapter(private val listStations: List<StationEntity>) : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: StationEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class StationViewHolder(private val binding: ItemStationsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(station: StationEntity) {
            with(binding) {
                tvItemName.text = station.name
                tvItemDesc.text = station.description
                Glide.with(itemView.context)
                        .load(station.image)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                        .into(imgItemPhoto)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val itemStationsBinding = ItemStationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StationViewHolder(itemStationsBinding)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = listStations[position]
        holder.bind(station)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listStations[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listStations.size
}