
package com.capstone.hibykes.ui.station

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.data.remote.response.PredictionResponse
import com.capstone.hibykes.databinding.ActivityStationBinding
import com.capstone.hibykes.ui.MainActivity
import com.capstone.hibykes.ui.prediction.PredictionActivity
import com.capstone.hibykes.ui.prediction.PredictionActivity.Companion.EXTRA_PREDICTION
import com.capstone.hibykes.viewmodel.ViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class StationActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STATION = "extra_station"
    }
    private lateinit var binding: ActivityStationBinding
    private lateinit var viewModel: StationViewModel
    private lateinit var station: StationEntity
    private lateinit var predictionData: List<PredictionEntity>
    private lateinit var predictionAdapter: PredictionAdapter
    private lateinit var datetime: String

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val date = Calendar.getInstance().time
        val datetimeFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        datetime = datetimeFormat.format(date)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[StationViewModel::class.java]

        station = intent.getParcelableExtra(EXTRA_STATION)!!
        predictionData = viewModel.getPredictionData().filter { it.station == station.id }


        viewModel.getPredictionModel(datetime, station.name!!).observe(this, {
            if (it != null) {
                val predictionMapped = mapPredictionResponsesToEntities(it)
                predictionChart(predictionMapped)
                getPredictions(predictionMapped)
            } else {
                binding.tvNull.visibility = VISIBLE
            }
        })

        populateStation()
    }

    private fun populateStation() {
        binding.apply {
            tvStationName.text = station.name
            tvStationAddress.text = station.address
            Glide.with(baseContext)
                    .load(station.image)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.imgBackdrop)
        }
    }

    private fun predictionChart(prediction: ArrayList<PredictionEntity>) {
        val anyChartView = binding.anyChartView
        anyChartView.setProgressBar(binding.progressBar)
        val cartesian = AnyChart.column()

        val chartData = prediction.map {
            ValueDataEntry(it.datetime, it.demandCount)
        }

        val column: Column = cartesian.column(chartData)
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)