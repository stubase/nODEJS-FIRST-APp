package com.capstone.hibykes.data.remote.api

import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?&units=metric&APPID=b79bc281c43f45a69cd831ebc73127d8")
    fun getWeatherData(
        @Query("q") cityName: String
    ): Call<WeatherResponse>

    @GET("data/2.5/air_pollution?&appid=b79bc281c4