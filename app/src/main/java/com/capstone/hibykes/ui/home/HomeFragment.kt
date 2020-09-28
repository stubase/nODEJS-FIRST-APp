package com.capstone.hibykes.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.databinding.FragmentHomeBinding
import com.capstone.hibykes.ui.listStation.ListStationActivity
import com.capstone.hibykes.ui.station.StationActivity
import com.capstone.hibykes.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var stationAdapter: StationAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        fragmentHomeBinding.btnViewAll.setOnClickListener {
            val intent = Intent(context, ListStationActivity::class.java)
            startActivity(intent)
        }
        auth = Firebase.auth
        val name = auth.currentUser?.displayName
        fragmentHomeBinding.tvHi.text = getString(R.string.hai, name)

        getWeatherData()
        getAirPollution()
        getStations()
    }

    private fun getStations() {
        viewModel.getStationsData().observe(viewLifecycleOwner, {

            stationAdapter = StationAdapter(it)
            stationAdapter.notifyDataSetChanged()

            fragmentHomeBinding.apply {
                shimmerRvStation.stopShimmer()
                shimmerRvStation.visibility = View.GONE
                rvStation.visibility = View.VISIBLE

                rvStation.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
          