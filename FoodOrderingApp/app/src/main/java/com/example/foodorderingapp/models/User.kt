package com.example.foodorderingapp.models

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val phone: String = "",
    val address: String = "",
    val profileImageUrl: String = "",
    val role: String = "user",
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)
