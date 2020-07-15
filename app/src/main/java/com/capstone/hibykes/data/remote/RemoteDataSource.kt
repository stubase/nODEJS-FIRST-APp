
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
    }

    fun getCurrentWeather(city: String): LiveData<WeatherResponse> {
        val weather: MutableLiveData<WeatherResponse> = MutableLiveData()
        val client = WeatherApiConfig.getApiService().getWeatherData(city)

        client.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    weather.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return weather
    }

    fun getCurrentAirPollution(lat: Double, lon: Double): LiveData<AirPollutionResponse> {
        val airPollution: MutableLiveData<AirPollutionResponse> = MutableLiveData()
        val client = WeatherApiConfig.getApiService().getAirPollutionData(lat, lon)

        client.enqueue(object : Callback<AirPollutionResponse> {
            override fun onResponse(call: Call<AirPollutionResponse>, response: Response<AirPollutionResponse>) {
                if (response.isSuccessful) {
                    airPollution.postValue(response.body())
                    Log.e(TAG, "onSuccess: ${response.body()}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AirPollutionResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return airPollution
    }

    fun getPredictionModel(date: String, station: String): LiveData<List<PredictionResponse>> {
        val prediction: MutableLiveData<List<PredictionResponse>> = MutableLiveData()

        ModelApiConfig.getApiService().getPredictionModel(date,station).enqueue(object: Callback<ListPredictionResponse>{
            override fun onResponse(
                call: Call<ListPredictionResponse>,
                response: Response<ListPredictionResponse>
            ) {
                if(response.isSuccessful){
                    prediction.postValue(response.body()?.prediction)
                    Log.e(TAG, "onSuccess: ${response.body()?.prediction}")
                }else{
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListPredictionResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return prediction
    }
}