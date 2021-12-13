package com.example.videoapp.view.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

        requestPermissions()

        setListeners()
    }

    private fun hasReadExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasManageOrWriteExternalStoragePermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }

    private fun requestPermissions() {
        var permissionToRequest = mutableListOf<String>()

        if (!hasManageOrWriteExternalStoragePermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                permissionToRequest.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            } else {
                permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

        if (!hasReadExternalStoragePermission()) {
            permissionToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionToRequest.toTypedArray(),
                0
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PermissionsRequest", "${permissions[i]} granted.")
                }
            }
        }
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