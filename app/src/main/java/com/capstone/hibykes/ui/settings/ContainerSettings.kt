package com.capstone.hibykes.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.capstone.hibykes.R
import com.capstone.hibykes.ui.login.LoginActivity
import com.capstone.hibykes.ui.profile.Profile
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ContainerSettings : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var profilePref : Preference
  