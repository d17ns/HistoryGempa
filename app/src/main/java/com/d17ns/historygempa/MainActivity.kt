package com.d17ns.historygempa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.d17ns.historygempa.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// class MainActivity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // variabel navView untuk mengimplementasikan fungsi bottom menu, dengan layout nav_view
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // variabel navController sebagai NavHostFragment, dengan layout nav_host_fragment
        val navController = findNavController(R.id.nav_host_fragment)

        // mengimplementasikan variabel navView dengan navController
        navView.setupWithNavController(navController)
    }
}