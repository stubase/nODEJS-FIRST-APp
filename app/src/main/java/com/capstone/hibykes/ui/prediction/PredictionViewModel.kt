package com.capstone.hibykes.ui.prediction

import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.PredictionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PredictionViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun insertBookmark(prediction: PredictionEntity) {
        return hiBykesRepositories.insertBookmark(prediction)
    }

    suspend fun check