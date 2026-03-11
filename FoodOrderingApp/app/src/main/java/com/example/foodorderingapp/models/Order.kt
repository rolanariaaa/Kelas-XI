package com.example.foodorderingapp.models

data class Order(
    val id: String = "",
    val userId: String = "",
    val restaurantId: String = "",
    val items: List<CartItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val status: OrderStatus = OrderStatus.PENDING,
    val orderDate: Long = System.currentTimeMillis(),
    val deliveryAddress: String = "",
    val specialInstructions: String = "",
    val paymentMethod: String = "Cash on Delivery",
    val estimatedDeliveryTime: Long = 0L,
    val actualDeliveryTime: Long = 0L
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    READY,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
