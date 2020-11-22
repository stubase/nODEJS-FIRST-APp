package com.capstone.hibykes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse

class HomeViewModel(private val hiBykesRepositories: HiBykesRepositories