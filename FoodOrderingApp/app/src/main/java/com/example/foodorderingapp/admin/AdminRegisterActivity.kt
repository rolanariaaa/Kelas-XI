package com.example.foodorderingapp.admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorderingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminRegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var nameInput: EditText
    private lateinit var restaurantNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_register)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize views
        nameInput = findViewById(R.id.adminNameInput)
        restaurantNameInput = findViewById(R.id.restaurantNameInput)
        emailInput = findViewById(R.id.adminRegisterEmailInput)
        passwordInput = findViewById(R.id.adminRegisterPasswordInput)
        confirmPasswordInput = findViewById(R.id.adminConfirmPasswordInput)
        registerButton = findViewById(R.id.adminRegisterButton)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val restaurantName = restaurantNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            if (validateInput(name, restaurantName, email, password, confirmPassword)) {
                registerAdmin(name, restaurantName, email, password)
            }
        }
    }

    private fun validateInput(
        name: String,
        restaurantName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            nameInput.error = "Name is required"
            return false
        }
        if (restaurantName.isEmpty()) {
            restaurantNameInput.error = "Restaurant name is required"
            return false
        }
        if (email.isEmpty()) {
            emailInput.error = "Email is required"
            return false
        }
        if (password.isEmpty()) {
            passwordInput.error = "Password is required"
            return false
        }
        if (password.length < 6) {
            passwordInput.error = "Password must be at least 6 characters"
            return false
        }
        if (password != confirmPassword) {
            confirmPasswordInput.error = "Passwords do not match"
            return false
        }
        return true
    }

    private fun registerAdmin(name: String, restaurantName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val userId = result.user?.uid ?: return@addOnSuccessListener
                
                // Create admin profile in Firestore
                val adminData = hashMapOf(
                    "name" to name,
                    "restaurantName" to restaurantName,
                    "email" to email,
                    "role" to "admin",
                    "createdAt" to com.google.firebase.Timestamp.now()
                )

                db.collection("users")
                    .document(userId)
                    .set(adminData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Admin account created successfully", Toast.LENGTH_LONG).show()
                        finish() // Return to login screen
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error creating profile: ${e.message}", Toast.LENGTH_LONG).show()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Registration failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
