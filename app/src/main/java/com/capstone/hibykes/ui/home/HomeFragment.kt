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
import com.capstone.hibykes.ui.listStation.ListS