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
        return fragmentBookmarkBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

        bookmarkAdapter = BookmarkAdapter(bookmarks)
        bookmarkAdapter.notifyDataSetChanged()

        bookmarkAdapter.setOnItemClickCallback(object : BookmarkAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PredictionEntity) {
                val detailIntent = Intent(context, PredictionActivity::class.java)
                detailIntent.putExtra(PredictionActivity.EXTRA_PREDICTION, data)
                startActivity(detailIntent)
            }
        })

        fragmentBookmarkBinding.apply {
            rvBookmark.layoutManager = GridLayoutManager(context, 2)
            rvBookmark.setHasFixedSize(true)
            rvBookmark.adapter = bookmarkAdapter
        }

        viewModel.getAllBookmark().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val list = mapList(it)
                bookmarkAdapter.setList(list)
            }
        })
    }

    private fun mapList(bookmarks: List<BookmarkEntity>): ArrayList<PredictionEntity> {
        val listPrediction = ArrayList<PredictionEntity>()
       