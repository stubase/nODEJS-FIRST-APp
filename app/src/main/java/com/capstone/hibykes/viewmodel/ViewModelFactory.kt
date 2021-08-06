
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
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mHiBykesRepositories) as T
            }
            modelClass.isAssignableFrom(StationViewModel::class.java) -> {
                StationViewModel(mHiBykesRepositories) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(mHiBykesRepositories) as T
            }
            modelClass.isAssignableFrom(ListStationViewModel::class.java) -> {
                ListStationViewModel(mHiBykesRepositories) as T
            }
            modelClass.isAssignableFrom(PredictionViewModel::class.java) -> {
                PredictionViewModel(mHiBykesRepositories) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(mHiBykesRepositories) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}