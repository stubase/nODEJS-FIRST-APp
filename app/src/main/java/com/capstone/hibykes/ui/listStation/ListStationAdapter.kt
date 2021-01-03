package com.capstone.hibykes.ui.listStation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.databinding.ItemListStationBinding

class ListStationAdapter(private val listStations: List<StationEntity>, val context: Context) : RecyclerView.Adapter<ListStationAdapter.ListStationViewHolder>(),
    Filterable {

    private lateinit var onItemClickCallback: OnItemClickCallback
    var filterList = ArrayList<StationEntity>()

    init{
        filterList = listStations as ArrayList<StationEntity>
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: StationEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListStationViewHolder(private val binding: ItemListStationBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(station: StationEntity) {
            with(binding) {
                tvItemName.text = station.name
                tvItemDesc.text = station.description
                Glide.with(itemView.context)
                    .load(station.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.i