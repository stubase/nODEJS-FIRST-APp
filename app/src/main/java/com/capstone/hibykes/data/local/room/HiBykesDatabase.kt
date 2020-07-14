package com.capstone.hibykes.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.hibykes.data.local.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class HiBykes