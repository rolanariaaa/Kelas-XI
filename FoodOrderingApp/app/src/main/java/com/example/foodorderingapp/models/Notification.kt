package com.example.foodorderingapp.models

data class Notification(
    val id: String = "",
    val title: String = "",
    val message: String = "",
    val type: NotificationType = NotificationType.ORDER,
    val isRead: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val orderId: String? = null,
    val foodItemId: String? = null
)

enum class NotificationType {
    ORDER,
    PROMOTION,
    GENERAL,
    SYSTEM
}
