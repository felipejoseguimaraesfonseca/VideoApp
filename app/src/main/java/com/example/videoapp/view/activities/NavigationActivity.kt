package com.example.videoapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivityNavigationBinding
import com.example.videoapp.view.fragments.*

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    private val homeFragment = HomeFragment()
    private val trendingTopicsFragment = TrendingTopicsFragment()
    private val uploadFragment = UploadFragment()
    private val subscriptionsFragment = SubscriptionsFragment()
    private val libraryFragment = LibraryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.trendig -> replaceFragment(trendingTopicsFragment)
                R.id.upload -> replaceFragment(uploadFragment)
                R.id.subscriptions -> replaceFragment(subscriptionsFragment)
                R.id.library -> replaceFragment(libraryFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}