
package com.capstone.hibykes.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    @NonNull
    val id: String,

    @ColumnInfo(name = "station_id")
    val stationId: String,

    @ColumnInfo(name = "time")
    val datetime: String,

    @ColumnInfo(name = "demand_count")
    val demandCount: Int,

    @ColumnInfo(name = "desc")
    val desc: String,
) : Parcelable