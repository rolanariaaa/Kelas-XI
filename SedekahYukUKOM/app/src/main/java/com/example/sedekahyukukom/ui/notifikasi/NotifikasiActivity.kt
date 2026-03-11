package com.example.sedekahyukukom.ui.notifikasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedekahyukukom.adapter.NotifikasiAdapter
import com.example.sedekahyukukom.databinding.ActivityNotifikasiBinding
import com.example.sedekahyukukom.model.Notifikasi
import java.util.Date

class NotifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotifikasiBinding
    private lateinit var adapter: NotifikasiAdapter
    private val notifikasiList = mutableListOf<Notifikasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadNotifikasi()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = NotifikasiAdapter(notifikasiList)
        binding.rvNotifikasi.layoutManager = LinearLayoutManager(this)
        binding.rvNotifikasi.adapter = adapter
    }

    private fun loadNotifikasi() {
        // Data dummy notifikasi
        notifikasiList.clear()
        notifikasiList.addAll(
            listOf(
                Notifikasi(
                    "1",
                    "Sedekah Berhasil! 🎉",
                    "Alhamdulillah, sedekah Anda sebesar Rp 50.000 telah berhasil. Barakallahu fiikum!",
                    Date(System.currentTimeMillis() - 3600000),
                    false,
                    "success"
                ),
                Notifikasi(
                    "2",
                    "Kampanye Baru 📢",
                    "Ada kampanye baru: Bantu Palestina. Yuk berdonasi!",
                    Date(System.currentTimeMillis() - 7200000),
                    false,
                    "info"
                ),
                Notifikasi(
                    "3",
                    "Target Tercapai! 🎯",
                    "Kampanye 'Bangun Masjid' telah mencapai target 100%!",
                    Date(System.currentTimeMillis() - 86400000),
                    true,
                    "achievement"
                ),
                Notifikasi(
                    "4",
                    "Naik Level! ⬆️",
                    "Selamat! Anda naik ke level 'Dermawan'. Lanjutkan berbagi kebaikan!",
                    Date(System.currentTimeMillis() - 172800000),
                    true,
                    "achievement"
                ),
                Notifikasi(
                    "5",
                    "Artikel Baru 📖",
                    "Baca artikel terbaru: 'Keutamaan Sedekah di Bulan Ramadan'",
                    Date(System.currentTimeMillis() - 259200000),
                    true,
                    "info"
                )
            )
        )
        adapter.notifyDataSetChanged()

        // Update badge count
        val unreadCount = notifikasiList.count { !it.isRead }
        binding.toolbar.title = "Notifikasi${if (unreadCount > 0) " ($unreadCount)" else ""}"
    }
}
