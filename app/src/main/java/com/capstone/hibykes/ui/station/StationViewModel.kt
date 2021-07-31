
package com.capstone.hibykes.ui.station

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.remote.response.PredictionResponse
import com.capstone.hibykes.utils.DataDummy

class StationViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun getPredictionData(): List<PredictionEntity> = DataDummy.generateDataPrediction()
    fun getPredictionModel(date: String, station: String): LiveData<List<PredictionResponse>> = hiBykesRepositories.getPredictionModel(date, station)
}