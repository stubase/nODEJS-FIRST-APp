package com.capstone.hibykes.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StationEntity (
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val address: String? = null,
    val latitude: S