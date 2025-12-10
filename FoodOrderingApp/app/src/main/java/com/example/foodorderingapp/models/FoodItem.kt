package com.example.foodorderingapp.models

data class FoodItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val category: String = "",
    val restaurantId: String = "",
    val isAvailable: Boolean = true
)
