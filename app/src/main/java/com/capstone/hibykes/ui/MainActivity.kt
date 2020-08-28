package com.capstone.hibykes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capstone.hibykes.R
import com.capstone.hibykes.databinding.ActivityMainBinding
import com.capstone.hibykes.ui.bookmark.BookmarkFragment
import com.capstone.hibykes.ui.home.HomeFragment
import com.capstone.hibykes.ui.maps.MapsFragment
import com.capstone.hibykes.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setItemSelected(R.id.navigation_home, true)

        supportFragmentManager
            .beginTransaction(