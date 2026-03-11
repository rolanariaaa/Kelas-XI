package com.example.sedekahyukukom.ui.riwayat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedekahyukukom.adapter.RiwayatAdapter
import com.example.sedekahyukukom.databinding.ActivityRiwayatBinding
import com.example.sedekahyukukom.model.Riwayat
import com.example.sedekahyukukom.utils.PreferenceManager
import com.google.firebase.Timestamp
import java.util.Date

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var adapter: RiwayatAdapter
    private val riwayatList = mutableListOf<Riwayat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        setupRecyclerView()
        loadRiwayat()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = RiwayatAdapter(riwayatList)
        binding.rvRiwayat.layoutManager = LinearLayoutManager(this)
        binding.rvRiwayat.adapter = adapter
    }

    private fun loadRiwayat() {
        val userId = preferenceManager.userId
        
        binding.progressBar.visibility = View.VISIBLE
        
        // Ambil data transaksi dari SharedPreferences
        riwayatList.clear()
        
        val savedRiwayat = preferenceManager.getRiwayatList()
        
        if (savedRiwayat.isEmpty()) {
            // Jika belum ada transaksi, tampilkan data demo
            val dummyRiwayat = listOf(
                Riwayat(
                    id = "demo1",
                    userId = userId ?: "",
                    kampanyeId = "",
                    kampanyeJudul = "Sedekah Umum (Demo)",
                    nominal = 50000L,
                    metodePembayaran = "GoPay",
                    tanggal = Timestamp(Date(System.currentTimeMillis() - 86400000)),
                    status = "Demo"
                ),
                Riwayat(
                    id = "demo2",
                    userId = userId ?: "",
                    kampanyeId = "",
                    kampanyeJudul = "Bantu Palestina (Demo)",
                    nominal = 100000L,
                    metodePembayaran = "OVO",
                    tanggal = Timestamp(Date(System.currentTimeMillis() - 172800000)),
                    status = "Demo"
                ),
                Riwayat(
                    id = "demo3",
                    userId = userId ?: "",
                    kampanyeId = "",
                    kampanyeJudul = "Sedekah Jariyah (Demo)",
                    nominal = 200000L,
                    metodePembayaran = "Transfer Bank",
                    tanggal = Timestamp(Date(System.currentTimeMillis() - 259200000)),
                    status = "Demo"
                )
            )
            riwayatList.addAll(dummyRiwayat)
        } else {
            // Tampilkan transaksi yang tersimpan
            riwayatList.addAll(savedRiwayat)
        }
        
        binding.progressBar.visibility = View.GONE
        adapter.notifyDataSetChanged()
        
        if (riwayatList.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvRiwayat.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvRiwayat.visibility = View.VISIBLE
        }
    }
}