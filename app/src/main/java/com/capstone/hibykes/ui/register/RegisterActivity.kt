package com.capstone.hibykes.ui.register

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.capstone.hibykes.R
import com.capstone.hibykes.databinding.ActivityRegisterBinding
import com.capstone.hibykes.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> registerUser()
            R.id.btn_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        binding.apply {

            if (email.isEmpty()) {
                etEmail.error = "Please enter Username!"
                etEmail.requestFocus()
                return
            }
            if (password.isEmpty()) {
                etPassword.error = "Please enter Password!"
                etPassword.requestFocus()
                return
            }
            if (password != confirmPassword) {
                etConfirmPassword.error = "Password is not same!"
                etConfirmPassword.requestFocus()
                return
            }
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {
                Log.d("Register", "createUserWithEmail:success")
                val user = auth.currentUser

                val profileUpdates = UserProfileChangeRequest.Builder().apply {
                    val name = email.takeWhile { it != '@' }
                    displayName = name
                }.build()
                user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Log.i("RegisterUpdate","User Profile Update")
                    }
                }
                user?.sendEmailVerification()?.addOnCompleteListener{
                    if (it.isSuccessful) {
                        Toast.makeText(baseContext, "Email verification send!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            } else {
                Log.w("Register", "createUserWithEmail:failure", task.exception)
                val message = "Authentication failed."
                showAlert(false,message)
            }
        }
    }

    private fun showAlert(check: Boolean, message: String) {
        dialog.setContentView(R.layout.dialog_alert)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage = dialog.findViewById(R.id.tv_message) as TextView
        tvMessage.text = message

        val btnClose = dialog.findViewById(R.id.iv_close) as ImageView
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        val lottie = dialog.findViewById(R.id.lottie_dialog) as LottieAnimationView

        if(!check){
            lottie.setAnimation("failed.json")
        }else{
            lottie.setAnimation("email_confirmation.json")
        }
        dialog.show()
    }
}