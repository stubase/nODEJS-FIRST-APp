package com.capstone.hibykes.data.local

import androidx.lifecycle.LiveData
import com.capstone.hibykes.data.local.entity.BookmarkEntity
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.room.HiBykesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource private constructor(private val mHiBykesDao: HiBykesDao) {

    com