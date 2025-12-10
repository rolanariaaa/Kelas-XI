package com.example.foodorderingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorderingapp.admin.AdminMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the action bar
        supportActionBar?.hide()

        // Wait for 2 seconds then check auth state
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserAndNavigate()
        }, SPLASH_DELAY)
    }

    private fun checkUserAndNavigate() {
        try {
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser == null) {
                // User not logged in, go to login
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                // Check user role in Firestore
                db.collection("users")
                    .document(currentUser.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        try {
                            val intent = when (document.getString("role")) {
                                "admin" -> Intent(this, AdminMainActivity::class.java)
                                else -> Intent(this, MainActivity::class.java)
                            }
                            startActivity(intent)
                            finish()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            // If error occurs, redirect to login
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }
                    .addOnFailureListener { e ->
                        e.printStackTrace()
                        // If error occurs, redirect to login
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // If error occurs, redirect to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val SPLASH_DELAY = 2000L // 2 seconds
    }
}
