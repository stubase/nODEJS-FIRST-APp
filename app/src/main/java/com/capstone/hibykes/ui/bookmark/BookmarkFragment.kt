package com.capstone.hibykes.ui.bookmark

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.hibykes.data.local.entity.BookmarkEntity
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.databinding.FragmentBookmarkBinding
import com.capstone.hibykes.ui.prediction.PredictionActivity
import com.capstone.hibykes.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var fragmentBookmarkBinding: FragmentBookmarkBinding
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private var bookmarks = ArrayList<PredictionEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return fragmentBookmar