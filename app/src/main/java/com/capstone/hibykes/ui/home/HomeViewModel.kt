package com.capstone.hibykes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse

class HomeViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun getCurrentWeather(city: String): LiveData<WeatherResponse> = hiBykesRepositories.getCurrentWeather(city)
    fun getAirPollution(lat: Double, lon: Double): LiveData<AirPollutionResponse> = hiBykesRepositories.getAirPollution(lat, lon)
    fun getStationsData(): LiveData<ArrayList<StationEntity>> = hiBykesRepositories.getStationsData()
}