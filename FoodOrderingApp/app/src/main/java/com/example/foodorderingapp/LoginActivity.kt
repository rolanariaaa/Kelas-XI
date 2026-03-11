package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.foodorderingapp.admin.AdminMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var adminCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set language for Firebase Auth messages
        FirebaseAuth.getInstance().setLanguageCode("en")

        // Initialize views
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        adminCheckBox = findViewById(R.id.adminCheckBox)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                performLogin(email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin(email: String, password: String) {
        // Basic validation
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show()
            return
        }
        
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show()
            return
        }

        // Show loading indicator
        Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
        
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                // Login successful
                result.user?.let { user ->
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    checkUserRoleAndNavigate(user.uid)
                }
            }
            .addOnFailureListener { exception ->
                // Login failed, try to create account
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                        result.user?.let { user ->
                            createUserProfile(email)
                        }
                    }
                    .addOnFailureListener { e ->
                        val errorMessage = when {
                            e.message?.contains("email address is badly formatted") == true -> 
                                "Invalid email format"
                            e.message?.contains("password is invalid") == true -> 
                                "Password must be at least 6 characters"
                            e.message?.contains("email address is already in use") == true -> 
                                "Email already registered. Please try logging in."
                            e.message?.contains("network error") == true ->
                                "Network error. Please check your internet connection."
                            else -> "Authentication error: ${e.message}"
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    }
            }
    }

    private fun createNewAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    task.result?.user?.let { user ->
                        createUserProfile(email)
                    }
                } else {
                    val errorMessage = when {
                        task.exception?.message?.contains("email address is already in use") == true -> 
                            "Email already registered"
                        task.exception?.message?.contains("password is invalid") == true -> 
                            "Password must be at least 6 characters"
                        else -> "Failed to create account: ${task.exception?.message}"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun checkUserRoleAndNavigate(userId: String) {
        Toast.makeText(this, "Checking user role...", Toast.LENGTH_SHORT).show()
        
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val role = document.getString("role")
                    Toast.makeText(this, "User role: $role", Toast.LENGTH_SHORT).show()
                    
                    val intent = when (role) {
                        "admin" -> Intent(this, AdminMainActivity::class.java)
                        else -> Intent(this, MainActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "User profile not found", Toast.LENGTH_SHORT).show()
                    // If document doesn't exist, create user profile
                    createUserProfile(auth.currentUser?.email ?: "")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, 
                    "Error checking user role: ${exception.message}", 
                    Toast.LENGTH_LONG).show()
            }
    }

    private fun createUserProfile(email: String) {
        val user = auth.currentUser
        if (user != null) {
            val userProfile = hashMapOf<String, Any>(
                "email" to email,
                "role" to if (adminCheckBox.isChecked) "admin" else "user",
                "createdAt" to com.google.firebase.Timestamp.now()
            )

            FirebaseFirestore.getInstance()
                .collection("users")
                .document(user.uid)
                .set(userProfile)
                .addOnSuccessListener {
                    checkUserRoleAndNavigate(user.uid)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Failed to create profile: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in and update UI accordingly
        val currentUser = auth.currentUser
        if (currentUser != null) {
            checkUserRoleAndNavigate(currentUser.uid)
        }
    }
}
