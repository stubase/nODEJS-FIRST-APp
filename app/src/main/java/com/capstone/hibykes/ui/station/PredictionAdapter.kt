package com.capstone.hibykes.ui.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.databinding.ItemPredictionsBinding

class PredictionAdapter(private val listPredictions: List<PredictionEntity>) : RecyclerView.Adapter<PredictionAdapter