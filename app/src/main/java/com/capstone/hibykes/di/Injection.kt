package com.capstone.hibykes.di

import android.content.Context
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.LocalDataSource
import com.capstone.hibykes.data.local.room.HiBykesDatabase
import com.capstone.hibykes.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): HiBykesRepositories {
        val database = HiBykesDatabase.getDatabase(context)
        val remoteDa