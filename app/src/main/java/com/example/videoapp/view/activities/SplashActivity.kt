package com.example.videoapp.view.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.videoapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //requestPermissions()

        splashTransaction()
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
        val permissionToRequest = mutableListOf<String>()

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

    private fun splashTransaction() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, SignInWithActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}