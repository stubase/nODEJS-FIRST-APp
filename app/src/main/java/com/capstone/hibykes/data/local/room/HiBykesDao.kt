
package com.capstone.hibykes.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.capstone.hibykes.data.local.entity.BookmarkEntity

@Dao
interface HiBykesDao {
    @Insert
    fun insert(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    fun getAllBookmark(): LiveData<List<BookmarkEntity>>

    @Query("SELECT count(*) FROM bookmarks WHERE bookmarks.id = :id")
    suspend fun checkBookmark(id: kotlin.String): Int

    @Query("DELETE FROM bookmarks WHERE bookmarks.id = :id")
    suspend fun deleteFromBookmark(id: kotlin.String): Int

}