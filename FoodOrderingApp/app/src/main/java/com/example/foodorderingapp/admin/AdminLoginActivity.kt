package com.example.foodorderingapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorderingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminLoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize views
        emailInput = findViewById(R.id.adminEmailInput)
        passwordInput = findViewById(R.id.adminPasswordInput)
        loginButton = findViewById(R.id.adminLoginButton)
        registerLink = findViewById(R.id.adminRegisterLink)

        // Set up click listeners
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (validateInput(email, password)) {
                loginAdmin(email, password)
            }
        }

        registerLink.setOnClickListener {
            startActivity(Intent(this, AdminRegisterActivity::class.java))
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            emailInput.error = "Email is required"
            return false
        }
        if (password.isEmpty()) {
            passwordInput.error = "Password is required"
            return false
        }
        return true
    }

    private fun loginAdmin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                // Check if user is admin
                val userId = authResult.user?.uid ?: return@addOnSuccessListener
                db.collection("users")
                    .document(userId)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.getString("role") == "admin") {
                            // Admin login successful
                            startActivity(Intent(this, AdminMainActivity::class.java))
                            finish()
                        } else {
                            // Not an admin account
                            Toast.makeText(this, "This account is not authorized as admin", Toast.LENGTH_LONG).show()
                            auth.signOut()
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        auth.signOut()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
