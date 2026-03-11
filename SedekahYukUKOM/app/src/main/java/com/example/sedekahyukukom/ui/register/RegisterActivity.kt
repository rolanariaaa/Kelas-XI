package com.example.sedekahyukukom.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityRegisterBinding
import com.example.sedekahyukukom.ui.home.HomeActivity
import com.example.sedekahyukukom.utils.PreferenceManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            if (validateInput(name, email, password, confirmPassword)) {
                registerUser(name, email, password)
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(
        name: String, 
        email: String, 
        password: String, 
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            binding.etName.error = "Nama tidak boleh kosong"
            return false
        }
        if (email.isEmpty()) {
            binding.etEmail.error = "Email tidak boleh kosong"
            return false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            return false
        }
        if (password.length < 6) {
            binding.etPassword.error = "Password minimal 6 karakter"
            return false
        }
        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Password tidak cocok"
            return false
        }
        
        binding.etName.error = null
        binding.etEmail.error = null
        binding.etPassword.error = null
        binding.etConfirmPassword.error = null
        return true
    }

    private fun registerUser(name: String, email: String, password: String) {
        binding.btnRegister.isEnabled = false
        
        // Demo mode: Simpan ke SharedPreferences saja
        val demoUserId = "demo_" + System.currentTimeMillis()
        
        preferenceManager.isLoggedIn = true
        preferenceManager.userId = demoUserId
        preferenceManager.userEmail = email
        preferenceManager.userName = name
        
        Toast.makeText(this, "Registrasi berhasil! (Demo Mode)", Toast.LENGTH_SHORT).show()
        
        // Langsung ke Home
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
        
        binding.btnRegister.isEnabled = true
    }
}
