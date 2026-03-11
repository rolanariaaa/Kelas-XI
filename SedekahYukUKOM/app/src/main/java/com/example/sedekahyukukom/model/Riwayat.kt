package com.example.sedekahyukukom.model

import com.google.firebase.Timestamp

data class Riwayat(
    val id: String = "",
    val userId: String = "",
    val kampanyeId: String = "",
    val kampanyeJudul: String = "",
    val nominal: Long = 0,
    val metodePembayaran: String = "",
    val tanggal: Timestamp = Timestamp.now(),
    val status: String = "Berhasil (Simulasi)"
)
