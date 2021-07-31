package com.capstone.hibykes.utils

import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.entity.StationEntity

object DataDummy {

    fun generateDataPrediction(): List<PredictionEntity> {
        val predictions = ArrayList<PredictionEntity>()
        predictions.add(PredictionEntity("1", "1", "1 June", 24