package com.example.sedekahyukukom.model

import java.util.Date

data class Notifikasi(
    val id: String = "",
    val title: String = "",
    val message: String = "",
    val timestamp: Date = Date(),
    val isRead: Boolean = false,
    val type: String = "info" // info, success, warning, achievement
)
