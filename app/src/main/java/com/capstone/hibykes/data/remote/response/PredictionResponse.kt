package com.capstone.hibykes.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictionResponse(

    @field:SerializedName("datetime")
    val datetime: 