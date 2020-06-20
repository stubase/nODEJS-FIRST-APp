
package com.capstone.hibykes.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.hibykes.data.local.LocalDataSource
import com.capstone.hibykes.data.local.entity.BookmarkEntity
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.data.remote.RemoteDataSource
import com.capstone.hibykes.data.remote.response.AirPollutionResponse
import com.capstone.hibykes.data.remote.response.PredictionResponse
import com.capstone.hibykes.data.remote.response.WeatherResponse
import com.google.firebase.database.*

class HiBykesRepositories private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
    ) {
    companion object {
        @Volatile
        private var instance: HiBykesRepositories? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource): HiBykesRepositories =
            instance ?: synchronized(this) {
                instance ?: HiBykesRepositories(remoteData, localData).apply { instance = this }
            }
    }
    private lateinit var database: DatabaseReference

    fun getCurrentWeather(city: String): LiveData<WeatherResponse> {
        return remoteDataSource.getCurrentWeather(city)
    }

    fun getAirPollution(lat: Double, lon: Double): LiveData<AirPollutionResponse> {
        return remoteDataSource.getCurrentAirPollution(lat, lon)
    }

    fun getStationsData() : LiveData<ArrayList<StationEntity>> {
        val stationResults = MutableLiveData<ArrayList<StationEntity>>()
        database = FirebaseDatabase.getInstance("https://hibykes-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Data Station")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stationList = ArrayList<StationEntity>()
                if (snapshot.exists()){
                    for (stationSnapshot in snapshot.children) {
                        val station = stationSnapshot.getValue(StationEntity::class.java)
                        val stationItem = StationEntity(
                                station?.id,
                                station?.name,
                                station?.description,
                                station?.address,
                                station?.latitude,
                                station?.longitude,
                                "https://storage.googleapis.com/bike-station-image/${station?.id}.jpeg"
                        )
                        stationList.add(stationItem)
                    }
                }
                stationResults.postValue(stationList)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("firebase", "failed")
            }
        })
        return stationResults
    }

    fun getPredictionModel(date: String, station: String): LiveData<List<PredictionResponse>> {
        return remoteDataSource.getPredictionModel(date, station)
    }

    fun getAllBookmark(): LiveData<List<BookmarkEntity>>  {
        return localDataSource.getAllBookmark()
    }

    fun insertBookmark(prediction: PredictionEntity) {
        return localDataSource.insert(prediction)
    }

    suspend fun checkBookmark(id: String): Int {
        return localDataSource.checkBookmark(id)
    }

    suspend fun deleteFromBookmark(id: String) {
        return localDataSource.deleteFromBookmark(id)
    }
}