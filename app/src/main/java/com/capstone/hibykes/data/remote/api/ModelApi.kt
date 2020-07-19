package com.capstone.hibykes.data.remote.api

import com.capstone.hibykes.data.remote.response.ListPredictionResponse
import retrofit2.Call
import retrofit2.http.*

interface ModelApi {
    @FormUrlEncoded
    @POST("/")
    fun getPredictionModel(
        @Field("date") date: String,
        @Field("station") station: String
    ): Call<ListPredictionResponse>

//    @POST("/")
//    fun getPredictionModel(@Body body: PredictionRequest?): Call<ListPredictionResponse>
}