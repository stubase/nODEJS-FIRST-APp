package com.capstone.hibykes.ui.listStation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.databinding.ActivityListStationBinding
import com.capstone.hibykes.ui.MainActivity
import com.capstone.hibykes.ui.station.StationActivity
import com.capstone.hibykes.viewmodel.ViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

class ListStationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListStationBinding
    private lateinit var stationAdapter: ListStationAdapter
    private lateinit var viewModel: ListStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_back_white)
        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor(ContextCompat.getColor(this, R.color.primary))

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ListStationViewModel::class.java]


        getStations()

        binding.searchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                stationAdapter?.filter.filter(newText)
                Log.d("search", newText!!)
                return false
            }

        })
    }

    private fun getStations() {
        viewModel.getStationsData().observe(this, {

            stationAdapter = ListStationAdapter(it,this)
            stationAdapter.notifyDataSetChanged()

            binding.apply {
                shimme