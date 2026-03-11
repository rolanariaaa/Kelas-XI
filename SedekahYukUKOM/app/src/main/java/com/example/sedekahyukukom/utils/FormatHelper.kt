package com.example.sedekahyukukom.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormatHelper {
    
    fun formatRupiah(amount: Long): String {
        val localeID = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localeID)
        return formatter.format(amount)
    }
    
    fun formatDate(timestamp: com.google.firebase.Timestamp): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale("id", "ID"))
        return sdf.format(timestamp.toDate())
    }
    
    fun formatDateOnly(timestamp: com.google.firebase.Timestamp): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return sdf.format(timestamp.toDate())
    }
}
