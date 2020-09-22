package com.capstone.hibykes.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hibykes.data.HiBykesRepositories
import com.capstone.hibykes.data.local.entity.BookmarkEntity

class BookmarkViewModel(private val hiBykesRepositories: HiBykesRepositories) : ViewModel() {
    fun getAllBookmark(): LiveData<List<BookmarkEntity>> {
        return hiBykesRepositories.getAllBookmark()
    }
}