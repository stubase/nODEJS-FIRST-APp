package com.capstone.hibykes.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.hibykes.data.local.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class HiBykesDatabase : RoomDatabase() {
    abstract fun hiBykesDao(): HiBykesDao

    companion object {
        @Volatile
        private var INSTANCE : HiBykesDatabase? = null

        fun getDatabase(context: Context) : HiBykesDatabase? {
            if (INSTANCE == null) {
                synchronized(HiBykesDatabase::class) {
                    INSTANCE= Room.databaseBuilder(context.applicationContext,
                        HiBykesDatabase::clas