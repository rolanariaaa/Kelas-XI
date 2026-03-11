package com.example.sedekahyukukom.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.ui.home.HomeActivity
import com.example.sedekahyukukom.ui.login.LoginActivity
import com.example.sedekahyukukom.utils.Constants
import com.example.sedekahyukukom.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferenceManager = PreferenceManager(this)

        // Display random quote
        val tvQuote = findViewById<TextView>(R.id.tvQuote)
        tvQuote.text = Constants.KUTIPAN_SEDEKAH.random()

        // Navigate using coroutine (non-blocking, more efficient)
        lifecycleScope.launch {
            delay(1000) // 1 second delay
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        try {
            val intent = if (preferenceManager.isLoggedIn) {
                Intent(this, HomeActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } catch (e: Exception) {
            e.printStackTrace()
            finish()
        }
    }
}
