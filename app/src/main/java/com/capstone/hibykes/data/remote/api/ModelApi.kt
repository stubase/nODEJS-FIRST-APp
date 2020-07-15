package com.capstone.hibykes.data.remote.api

import com.capstone.hibykes.data.remote.response.ListPredictionResponse
import retrofit2.Call
import retrofit2.http.*

interface ModelApi {
    @FormUrlEncoded
    @POST("/")
    fun 