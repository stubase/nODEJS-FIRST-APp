package com.capstone.hibykes.ui.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.databinding.ItemPredictionsBinding

class PredictionAdapter(private val listPredictions: List<PredictionEntity>) : RecyclerView.Adapter<PredictionAdapter.PredictionViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: PredictionEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class PredictionViewHolder(private val binding: ItemPredictionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prediction: PredictionEntity) {
            with(binding) {
                val date = prediction.datetime.substring(0,10)
                val time = prediction.datetime.substring(11,16)
                tvItemDatetime.text = date + "\n" + time
                tvItemDemand.text = prediction.demandCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionViewHolder {
        val itemPredictionsBinding = ItemPredictionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PredictionViewHolder(itemPredictionsBinding)
    }

    override fun onBindViewHolder(holder: PredictionViewHolder, position: Int) {
        val prediction = listPredictions[position]
        holder.bind(prediction)
        holder.it