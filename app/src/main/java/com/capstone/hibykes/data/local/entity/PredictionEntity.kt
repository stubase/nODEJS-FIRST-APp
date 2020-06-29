package com.capstone.hibykes.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictionEntity (
    val id: String,
    val station: String,
    val datetime: String,
    val demandCount: Int,
    val desc: String,
)  : Parcelable