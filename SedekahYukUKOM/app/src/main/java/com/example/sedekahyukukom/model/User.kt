package com.example.sedekahyukukom.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val totalSedekah: Long = 0,
    val saldoDummy: Long = 1000000 // Saldo awal 1 juta rupiah (dummy)
)
