package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodorderingapp.ui.user.*
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // Initialize Firebase Auth
            auth = FirebaseAuth.getInstance()

            // Cek apakah user sudah login
            val currentUser = auth.currentUser
            if (currentUser == null) {
                // Jika belum login, arahkan ke LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                return
            }

            // Setup bottom navigation
            setupBottomNavigation()

            // Load HomeFragment by default
            if (savedInstanceState == null) {
                loadFragment(HomeFragment())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // If there's an error, finish the activity
            finish()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            try {
                val fragment = when (item.itemId) {
                    R.id.menuHome -> HomeFragment()
                    R.id.menuSearch -> SearchFragment()
                    R.id.menuCart -> CartFragment()
                    R.id.menuOrders -> OrdersFragment()
                    R.id.menuProfile -> ProfileFragment()
                    else -> return@setOnItemSelectedListener false
                }
                loadFragment(fragment)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}