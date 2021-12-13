package com.example.videoapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivitySignUpWithAnEmailBinding
import com.example.videoapp.viewmodel.UserViewModel

class SignUpWithAnEmailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignUpWithAnEmailBinding

    private lateinit var mViewModel: UserViewModel
    private var mUserId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpWithAnEmailBinding.inflate(layoutInflater)
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

        val firstName = binding.fistNameDarkOrangeEditText.text.toString()
        val lastName = binding.lastNameDarkOrangeEditText.text.toString()
        val email = binding.emailDarkOrangeEditText.text.toString()
        val confirmEmail = binding.confirmEmailDarkOrangeEditText.text.toString()
        val password = binding.passwordDarkOrangeEditText.text.toString()
        val confirmPassword = binding.confirmPasswordDarkOrangeEditText.text.toString()

        if (idClick == R.id.cancel_white_button) {
            val intent = Intent(this, SignInOrSignUpActivity::class.java)
            startActivity(intent)
            finish()
        } else if (idClick == R.id.sign_up_dark_orange_button) {
            if (
                firstName == "" &&
                lastName == "" &&
                email == "" &&
                confirmEmail == "" &&
                password == "" &&
                confirmPassword == ""
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.fill_all_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (
                firstName == "" ||
                lastName == "" ||
                email == "" ||
                confirmEmail == "" ||
                password == "" ||
                confirmPassword == ""
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.fill_all_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (email != confirmEmail && password != confirmPassword) {
                    Toast.makeText(
                        this,
                        getString(R.string.are_not_the_same),
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (email != confirmEmail || password != confirmPassword) {
                    if (email != confirmEmail) {
                        Toast.makeText(
                            this,
                            getString(R.string.email_and_confirm_email_are_not_the_same),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(
                            this,
                            getString(R.string.password_and_confirm_password_are_not_the_same),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    mViewModel.saveUser(mUserId, firstName, lastName, email, password)
                    val intent = Intent(this, SignUpWithAnEmailActivity::class.java)
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
        binding.signUpDarkOrangeButton.setOnClickListener(this)
    }

}