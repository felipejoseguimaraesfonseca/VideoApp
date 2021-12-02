package com.example.videoapp.view.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.videoapp.R
import com.example.videoapp.data.repository.AppDatabase
import java.io.File

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        bootMode()

        splashTransaction()
    }

    private fun bootMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val context = createDeviceProtectedStorageContext()

            context.getSharedPreferences("DeviceProtectedStorage", Context.MODE_PRIVATE)
            context.openFileOutput("sample.txt", MODE_PRIVATE)
            RoomDatabase.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
        }
    }

    private fun splashTransaction() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, SignInOrSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
