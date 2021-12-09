package com.example.videoapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivitySignInWithAnEmailBinding
import com.example.videoapp.viewmodel.UserViewModel

class SignInWithAnEmailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignInWithAnEmailBinding
    private lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInWithAnEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_VideoApp)
        setContentView(view)

        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        observe()

        setListeners()
    }

    override fun onClick(view: View) {
        val idClick = view.id

        val email = binding.emailDarkOrangeEditText.text.toString()
        val password = binding.passwordDarkOrangeEditText.text.toString()

        if (idClick == R.id.cancel_white_button) {
            val intent = Intent(this, SignInOrSignUpActivity::class.java)
            startActivity(intent)
            finish()
        } else if (idClick == R.id.sign_in_dark_orange_button) {
            if (email == "" && password == "") {
                Toast.makeText(
                    this, getString(R.string.fill_all_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (email == "" || password == "") {
                Toast.makeText(
                    this, getString(R.string.fill_all_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val observer = observe()

                if (observer.toString().toInt() == R.string.account_logged_successfully) {
                    val intent = Intent(this, NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun observe() {
        mViewModel.messageEventData.observe(this) { stringResId ->
            Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        binding.cancelWhiteButton.setOnClickListener(this)
        binding.signInDarkOrangeButton.setOnClickListener(this)
    }

}