package com.example.videoapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivitySignInOrSignUpBinding

class SignInOrSignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignInOrSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInOrSignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_VideoApp)
        setContentView(view)

        setListeners()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.sign_in_with_an_email_button) {
            val intent = Intent(this, SignInWithAnEmailActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (id == R.id.sign_in_with_facebook_button) {
            val intent = Intent(this, SignInWithFacebookActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (id == R.id.sign_in_with_google_button) {
            val intent = Intent(this, SignInWithGoogleActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (id == R.id.sign_up_with_an_email_button) {
            val intent = Intent(this, SignUpWithAnEmailActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (id == R.id.sign_up_with_facebook_button) {
            val intent = Intent(this, SignUpWithFacebookActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (id == R.id.sign_up_with_google_button) {
            val intent = Intent(this, SignUpWithGoogleActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setListeners() {
        binding.signInWithAnEmailButton.setOnClickListener(this)
        binding.signInWithFacebookButton.setOnClickListener(this)
        binding.signInWithGoogleButton.setOnClickListener(this)

        binding.signUpWithAnEmailButton.setOnClickListener(this)
        binding.signUpWithFacebookButton.setOnClickListener(this)
        binding.signUpWithGoogleButton.setOnClickListener(this)
    }

}