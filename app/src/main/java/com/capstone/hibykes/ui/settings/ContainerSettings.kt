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
    private lateinit var languagePref : Preference
    private lateinit var faqsPref : Preference
    private lateinit var communityPref : Preference
    private lateinit var privacyPref : Preference
    private lateinit var termsPref : Preference
    private lateinit var logoutPref : Preference

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        TODO("Not yet implemented")
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        profilePref = findPreference<Preference>(getString(R.string.key_profile)) as Preference
        languagePref = findPreference<Preference>(getString(R.string.key_language)) as Preference
        faqsPref = findPreference<Preference>(getString(R.string.key_faqs)) as Preference
        communityPref = findPreference<Preference>(getString(R.string.key_community)) as Preference
        privacyPref = findPreference<Preference>(getString(R.string.key_privacy)) as Preference
        termsPref = findPreference<Preference>(getString(R.string.key_terms)) as Preference
        logoutPref = findPreference<Preference>(getString(R.string.key_logout)) as Preference

        setSummary()
        languageOnClick()
        profileOnClick()
        helpOnClick()
        logoutOnClick()
    }

    private fun logoutOnClick() {
        logoutPref.setOnPreferenceClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            true
        }
    }

    private fun helpOnClick() {
        faqsPref.setOnPreferenceClickListener{
            val url ="https://sites.google.com/bangkit.academy/bangkitacademy2021/resources/graduation-details?authuser=0"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
            true
        }

        communityPref.setOnPreferenceClickListener{
            val url ="https://sites.google.com/bangkit.academy/bangkitacademy2021/resources/graduation-details?authuser=0"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
            true
        }

        privacyPref.setOnPreferenceClickListener{
            val url ="https://sites.google.com/bangkit.academy/bangkitacademy2021/resources/graduation-details?authuser=0"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
            true
        }

        termsPref.setOnPreferenceClickListener{
            val url ="https://sites.google.com/bangkit.academy/bangkitacademy2021/resources/graduation-details?authuser=0"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
            true
        }
    }

    private fun profileOnClick() {
        profilePref.setOnPreferenceClickListener{
            val intent = Intent(activity, Profile::class.java)
            startActivity(intent)
            true
        }
    }

