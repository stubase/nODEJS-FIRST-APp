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
        super