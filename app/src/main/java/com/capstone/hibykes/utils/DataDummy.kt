package com.capstone.hibykes.utils

import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.entity.StationEntity

object DataDummy {

    fun generateDataPrediction(): List<PredictionEntity> {
        val predictions = ArrayList<PredictionEntity>()
        predictions.add(PredictionEntity("1", "1", "1 June", 24, "low demand"))
        predictions.add(PredictionEntity("2", "1", "2 June", 33, "high demand"))
        predictions.add(PredictionEntity("3", "1", "3 June", 13, "low demand"))
        predictions.add(PredictionEntity("4", "1", "4 June", 39, "high demand"))
        predictions.add(PredictionEntity("5", "1", "5 June", 46, "high demand"))
        predictions.add(PredictionEntity("6", "2", "1 June", 18, "low demand"))
        predictions.add(PredictionEntity("7", "2", "2 June", 22, "high demand"))
        predictions.add(PredictionEntity("8", "2", "3 June", 20, "high demand"))
        predictions.add(PredictionEntity("9", "2", "4 June", 50, "high demand"))
        return predictions
    }
}