package com.example.foodorderingapp.models

data class CartItem(
    val id: String = "",
    val foodItem: FoodItem = FoodItem(),
    val quantity: Int = 1,
    val specialInstructions: String = "",
    val addedAt: Long = System.currentTimeMillis()
) {
    fun getTotalPrice(): Double = foodItem.price * quantity
}
