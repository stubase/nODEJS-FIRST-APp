
package com.capstone.hibykes.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.di.Injection
import com.capstone.hibykes.ui.bookmark.BookmarkViewModel
import com.capstone.hibykes.ui.home.HomeViewModel
import com.capstone.hibykes.ui.listStation.ListStationViewModel
import com.capstone.hibykes.ui.maps.MapsViewModel
import com.capstone.hibykes.ui.prediction.PredictionViewModel
import com.capstone.hibykes.ui.station.StationViewModel

class ViewModelFactory private constructor(private val mHiBykesRepositories: HiBykesRepositories)
    : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =