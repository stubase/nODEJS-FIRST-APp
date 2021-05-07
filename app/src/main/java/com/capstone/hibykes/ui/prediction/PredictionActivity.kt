
package com.capstone.hibykes.ui.prediction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.databinding.ActivityPredictionBinding
import com.capstone.hibykes.ui.MainActivity
import com.capstone.hibykes.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PredictionActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_PREDICTION = "extra_prediction"
    }
    private lateinit var binding: ActivityPredictionBinding
    private lateinit var viewModel: PredictionViewModel
    private lateinit var prediction: PredictionEntity
    private var statusBookmark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_white)
        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[PredictionViewModel::class.java]

        prediction = intent.getParcelableExtra(EXTRA_PREDICTION)!!
        populatePrediction()
        checkBookmark()
        binding.btnSaveBookmark.setOnClickListener(this)
        binding.btnRemoveBookmark.setOnClickListener(this)
    }

    private fun populatePrediction() {
        binding.apply {

            tvStation.text = prediction.station
            tvDemand.text = prediction.demandCount.toString()

            val date = prediction.datetime.substring(0,10)
            val time = prediction.datetime.substring(11,16)
            tvDatetime.text = date + "\n" + time

        }
    }
    private fun checkBookmark() {
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkBookmark(prediction.id)
            withContext(Dispatchers.Main) {
                setBookmarkState(count)
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_save_bookmark -> {
                viewModel.insertBookmark(prediction)