package com.example.sedekahyukukom.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityLoginBinding
import com.example.sedekahyukukom.ui.home.HomeActivity
import com.example.sedekahyukukom.ui.register.RegisterActivity
import com.example.sedekahyukukom.utils.PreferenceManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                loginUser(email, password)
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "Email tidak boleh kosong"
            return false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            return false
        }
        binding.etEmail.error = null
        binding.etPassword.error = null
        return true
    }

    private fun loginUser(email: String, password: String) {
        binding.btnLogin.isEnabled = false
        
        // Demo mode: Terima semua login
        val demoUserId = "demo_" + System.currentTimeMillis()
        
        preferenceManager.isLoggedIn = true
        preferenceManager.userId = demoUserId
        preferenceManager.userEmail = email
        preferenceManager.userName = "Demo User"
        
        Toast.makeText(this, "Login berhasil! (Demo Mode)", Toast.LENGTH_SHORT).show()
        
        // Navigate to Home
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
        
        binding.btnLogin.isEnabled = true
    }
}
