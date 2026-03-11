package com.example.sedekahyukukom.model

data class Kampanye(
    val id: String = "",
    val judul: String = "",
    val deskripsi: String = "",
    val kategori: String = "",
    val targetDana: Long = 0,
    val terkumpul: Long = 0,
    val gambar: Int = 0, // Resource ID untuk drawable
    val urgency: String = "Bantuan segera dibutuhkan" // Urgency message
) {
    fun getProgress(): Int {
        if (targetDana == 0L) return 0
        return ((terkumpul.toDouble() / targetDana.toDouble()) * 100).toInt()
    }
}
