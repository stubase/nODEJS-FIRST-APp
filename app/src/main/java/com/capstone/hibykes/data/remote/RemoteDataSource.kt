
package com.capstone.hibykes.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.hibykes.data.remote.api.ModelApiConfig
import com.capstone.hibykes.data.remote.api.WeatherApiConfig
import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.ListPredictionResponse
import com.capstone.hibykes.data.remote.response.PredictionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
        private const val TAG = "RemoteDataSource"