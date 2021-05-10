
package com.capstone.hibykes.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.hibykes.R
import com.capstone.hibykes.databinding.ActivityProfileBinding
import com.capstone.hibykes.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val name = auth.currentUser?.displayName
        val email = auth.currentUser?.email
        binding.tvName.text = name
        binding.tvEmail.text = email

        binding.toolbar.setNavigationIcon(R.drawable.ic_back_white)
        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}