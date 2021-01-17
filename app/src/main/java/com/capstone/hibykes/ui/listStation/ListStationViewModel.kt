
package com.capstone.hibykes.ui.listStation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse

class ListStationViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun getStationsData(): LiveData<ArrayList<StationEntity>> = hiBykesRepositories.getStationsData()
}