package com.example.sedekahyukukom.ui.profil

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.databinding.ActivityProfilBinding
import com.example.sedekahyukukom.ui.login.LoginActivity
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        loadUserData()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadUserData() {
        // Ambil data real dari PreferenceManager
        val userName = preferenceManager.userName ?: "Demo User"
        val userEmail = preferenceManager.userEmail ?: "demo@example.com"
        val totalSedekah = preferenceManager.totalSedekah
        val saldo = preferenceManager.saldo
        val transaksiCount = preferenceManager.transaksiCount
        
        binding.tvName.text = userName
        binding.tvEmail.text = userEmail
        binding.tvTotalSedekah.text = FormatHelper.formatRupiah(totalSedekah)
        binding.tvSaldo.text = FormatHelper.formatRupiah(saldo)
    }

    private fun setupClickListeners() {
        binding.btnEditProfil.setOnClickListener {
            showEditProfilDialog()
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showEditProfilDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_profil, null)
        val etName = dialogView.findViewById<EditText>(R.id.etName)
        etName.setText(binding.tvName.text)

        AlertDialog.Builder(this)
            .setTitle("Edit Profil")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val newName = etName.text.toString().trim()
                if (newName.isNotEmpty()) {
                    updateUserName(newName)
                } else {
                    Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun updateUserName(newName: String) {
        // Demo mode: Update ke SharedPreferences saja
        preferenceManager.userName = newName
        binding.tvName.text = newName
        Toast.makeText(this, "Profil berhasil diperbarui (Demo Mode)", Toast.LENGTH_SHORT).show()
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                logout()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        loadUserData() // Refresh data when returning
    }

    private fun logout() {
        preferenceManager.clearPreferences()
        
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
