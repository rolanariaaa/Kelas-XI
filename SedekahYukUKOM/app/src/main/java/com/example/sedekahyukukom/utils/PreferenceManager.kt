package com.example.sedekahyukukom.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.sedekahyukukom.model.Riwayat

class PreferenceManager(context: Context) {
    
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("SedekahYukPrefs", Context.MODE_PRIVATE)
    
    private val gson = Gson()
    
    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_RIWAYAT_LIST = "riwayat_list"
        private const val KEY_SALDO = "saldo_wallet"
        private const val KEY_TOTAL_SEDEKAH = "total_sedekah"
        private const val KEY_TRANSAKSI_COUNT = "transaksi_count"
        private const val KEY_KAMPANYE_DONATIONS = "kampanye_donations"
    }
    
    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
    
    var userId: String?
        get() = prefs.getString(KEY_USER_ID, null)
        set(value) = prefs.edit().putString(KEY_USER_ID, value).apply()
    
    var userEmail: String?
        get() = prefs.getString(KEY_USER_EMAIL, null)
        set(value) = prefs.edit().putString(KEY_USER_EMAIL, value).apply()
    
    var userName: String?
        get() = prefs.getString(KEY_USER_NAME, null)
        set(value) = prefs.edit().putString(KEY_USER_NAME, value).apply()
    
    // Manajemen Saldo Dompet
    var saldo: Long
        get() = prefs.getLong(KEY_SALDO, 0L)
        set(value) = prefs.edit().putLong(KEY_SALDO, value).apply()
    
    var totalSedekah: Long
        get() = prefs.getLong(KEY_TOTAL_SEDEKAH, 0L)
        set(value) = prefs.edit().putLong(KEY_TOTAL_SEDEKAH, value).apply()
    
    var transaksiCount: Int
        get() = prefs.getInt(KEY_TRANSAKSI_COUNT, 0)
        set(value) = prefs.edit().putInt(KEY_TRANSAKSI_COUNT, value).apply()
    
    // Tambah saldo
    fun addSaldo(amount: Long) {
        saldo += amount
    }
    
    // Kurangi saldo (return true jika berhasil, false jika saldo tidak cukup)
    fun deductSaldo(amount: Long): Boolean {
        return if (saldo >= amount) {
            saldo -= amount
            totalSedekah += amount
            transaksiCount += 1
            true
        } else {
            false
        }
    }
    
    // Simpan dan ambil riwayat transaksi
    fun saveRiwayat(riwayat: Riwayat) {
        val currentList = getRiwayatList().toMutableList()
        currentList.add(0, riwayat) // Tambah di awal list (transaksi terbaru)
        
        val json = gson.toJson(currentList)
        prefs.edit().putString(KEY_RIWAYAT_LIST, json).apply()
    }
    
    fun getRiwayatList(): List<Riwayat> {
        val json = prefs.getString(KEY_RIWAYAT_LIST, null) ?: return emptyList()
        val type = object : TypeToken<List<Riwayat>>() {}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun clearRiwayat() {
        prefs.edit().remove(KEY_RIWAYAT_LIST).apply()
    }
    
    // Simpan donasi per kampanye
    fun addKampanyeDonation(kampanyeId: String, amount: Long) {
        val currentAmount = getKampanyeDonation(kampanyeId)
        prefs.edit().putLong("$KEY_KAMPANYE_DONATIONS$kampanyeId", currentAmount + amount).apply()
    }
    
    // Ambil total donasi per kampanye
    fun getKampanyeDonation(kampanyeId: String): Long {
        return prefs.getLong("$KEY_KAMPANYE_DONATIONS$kampanyeId", 0L)
    }
    
    fun clearPreferences() {
        prefs.edit().clear().apply()
    }
}
