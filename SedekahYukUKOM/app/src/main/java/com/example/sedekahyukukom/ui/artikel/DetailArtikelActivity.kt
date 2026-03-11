package com.example.sedekahyukukom.ui.artikel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityDetailArtikelBinding

class DetailArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        loadArtikelData()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadArtikelData() {
        val judul = intent.getStringExtra("ARTIKEL_JUDUL") ?: ""
        val konten = intent.getStringExtra("ARTIKEL_KONTEN") ?: ""
        val kategori = intent.getStringExtra("ARTIKEL_KATEGORI") ?: ""

        binding.tvJudul.text = judul
        binding.tvKategori.text = kategori
        binding.tvKonten.text = konten
    }
}
