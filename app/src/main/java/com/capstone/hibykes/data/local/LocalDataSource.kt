package com.capstone.hibykes.data.local

import androidx.lifecycle.LiveData
import com.capstone.hibykes.data.local.entity.BookmarkEntity
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.room.HiBykesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource private constructor(private val mHiBykesDao: HiBykesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(hiBykesDao: HiBykesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(hiBykesDao)
    }

    fun getAllBookmark(): LiveData<List<BookmarkEntity>> {
        return mHiBykesDao.getAllBookmark()
    }

    fun insert(prediction: PredictionEntity) {
        insertToDb(prediction)
    }

    private fun insertToDb(prediction: PredictionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmark = BookmarkEntity(
                prediction.id,
                prediction.station,
                prediction.datetime.substring(0,10) + " / " + prediction.datetime.substring(11,16),
                prediction.demandCount,
                prediction.desc
            )
            mHiBykesDao.insert(bookmark)
 