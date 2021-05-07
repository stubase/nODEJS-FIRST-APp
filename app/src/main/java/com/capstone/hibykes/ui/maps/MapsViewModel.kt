
package com.capstone.hibykes.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.StationEntity

class MapsViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun getStationsData(): LiveData<ArrayList<StationEntity>> {
        return hiBykesRepositories.getStationsData()
    }
}