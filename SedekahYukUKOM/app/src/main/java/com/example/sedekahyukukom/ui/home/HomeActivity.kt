package com.example.sedekahyukukom.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityHomeSalingjagaBinding
import com.example.sedekahyukukom.ui.artikel.ArtikelActivity
import com.example.sedekahyukukom.ui.dampak.DampakActivity
import com.example.sedekahyukukom.ui.kampanye.KampanyeActivity
import com.example.sedekahyukukom.ui.leaderboard.LeaderboardActivity
import com.example.sedekahyukukom.ui.notifikasi.NotifikasiActivity
import com.example.sedekahyukukom.ui.profil.ProfilActivity
import com.example.sedekahyukukom.ui.riwayat.RiwayatActivity
import com.example.sedekahyukukom.ui.salingjaga.SalingJagaActivity
import com.example.sedekahyukukom.ui.sedekah.SedekahActivity
import com.example.sedekahyukukom.ui.statistik.StatistikActivity
import com.example.sedekahyukukom.ui.zakat.ZakatActivity
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSalingjagaBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSalingjagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        loadUserData()
        setupClickListeners()
        startAnimations()
    }

    override fun onResume() {
        super.onResume()
        loadUserData() // Reload data when returning from other activities
    }

    private fun loadUserData() {
        // Update saldo dan statistik di Dompet Kebaikan
        val saldo = preferenceManager.saldo
        val totalSedekah = preferenceManager.totalSedekah
        val transaksiCount = preferenceManager.transaksiCount
        
        // Update UI
        binding.tvSaldo.text = FormatHelper.formatRupiah(saldo)
        binding.tvTotalSedekah.text = FormatHelper.formatRupiah(totalSedekah)
        binding.tvTransaksiCount.text = "${transaksiCount}x"
    }

    private fun startAnimations() {
        // Optimized: Kurangi delay dan simplify animasi untuk mencegah lag
        val scaleIn = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.scale_in)
        val slideBottom = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_bottom)
        
        // Animasi menu cards dengan delay lebih singkat
        binding.menuDonasi.postDelayed({
            binding.menuDonasi.startAnimation(slideBottom)
        }, 100)
        
        binding.menuDonasiAbadi.postDelayed({
            binding.menuDonasiAbadi.startAnimation(slideBottom)
        }, 150)
        
        // Animasi campaign cards
        binding.cardKampanye1.postDelayed({
            binding.cardKampanye1.startAnimation(scaleIn)
        }, 250)
        
        binding.cardKampanye2.postDelayed({
            binding.cardKampanye2.startAnimation(scaleIn)
        }, 300)
    }

    private fun setupClickListeners() {
        // Animation on click
        val buttonClick = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.button_click)
        
        // Top bar buttons
        binding.btnNotification.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, NotifikasiActivity::class.java))
        }
        
        binding.btnProfile.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, ProfilActivity::class.java))
        }
        
        // Dompet Kebaikan - Isi Saldo
        binding.btnIsiSaldo.setOnClickListener {
            it.startAnimation(buttonClick)
            showIsiSaldoDialog()
        }
        
        binding.tvLihatRiwayat.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
        
        binding.btnRiwayatDompet.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
        
        binding.btnAktifkanDonasi.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, SedekahActivity::class.java))
        }
        
        // Menu Grid
        binding.menuDonasi.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, SedekahActivity::class.java))
        }
        
        binding.menuDonasiAbadi.setOnClickListener {
            it.startAnimation(buttonClick)
            showDonasiAbadiDialog()
        }
        
        binding.menuExperience.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, ArtikelActivity::class.java))
        }
        
        binding.menuKolaborasi.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, KampanyeActivity::class.java))
        }
        
        binding.menuZakat.setOnClickListener {
            it.startAnimation(buttonClick)
            showZakatDialog()
        }
        
        binding.menuLihatSemua.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, KampanyeActivity::class.java))
        }
        
        // Campaign Cards
        binding.cardKampanye1.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, KampanyeActivity::class.java))
        }
        
        binding.cardKampanye2.setOnClickListener {
            it.startAnimation(buttonClick)
            startActivity(Intent(this, KampanyeActivity::class.java))
        }
        
        // Category Chips
        binding.chipBencanaAlam.setOnClickListener {
            it.startAnimation(buttonClick)
            showCategoryInfo("Bencana Alam", "Bantu korban bencana alam di Indonesia seperti banjir, gempa, tsunami, dan longsor.")
        }
        
        binding.chipBalitaSakit.setOnClickListener {
            it.startAnimation(buttonClick)
            showCategoryInfo("Balita & Anak Sakit", "Bantu biaya pengobatan balita dan anak-anak yang sedang sakit dan membutuhkan bantuan medis.")
        }
        
        binding.chipBantuanMedis.setOnClickListener {
            it.startAnimation(buttonClick)
            showCategoryInfo("Bantuan Medis", "Bantu pasien yang membutuhkan biaya operasi, obat-obatan, dan perawatan medis lainnya.")
        }
        
        binding.chipPendidikan.setOnClickListener {
            it.startAnimation(buttonClick)
            showCategoryInfo("Pendidikan", "Bantu anak-anak kurang mampu untuk mendapatkan akses pendidikan yang layak.")
        }
        
        binding.chipPangan.setOnClickListener {
            it.startAnimation(buttonClick)
            showCategoryInfo("Pangan Bergizi", "Bantu masyarakat kurang mampu mendapatkan makanan bergizi untuk keluarga mereka.")
        }
        
        // Bottom Navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.sedekahyukukom.R.id.nav_home -> {
                    true
                }
                com.example.sedekahyukukom.R.id.nav_donasi -> {
                    startActivity(Intent(this, SedekahActivity::class.java))
                    true
                }
                com.example.sedekahyukukom.R.id.nav_salingjaga -> {
                    startActivity(Intent(this, SalingJagaActivity::class.java))
                    true
                }
                com.example.sedekahyukukom.R.id.nav_dampak -> {
                    startActivity(Intent(this, DampakActivity::class.java))
                    true
                }
                com.example.sedekahyukukom.R.id.nav_donasi_rutin -> {
                    startActivity(Intent(this, RiwayatActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
    
    private fun showPahalaSubuhDialog() {
        AlertDialog.Builder(this)
            .setTitle("Pahala Subuh Melimpah")
            .setMessage("Sedekah Tiap Subuh. Giliranmu jadi bagian Pejuang Subuh!\n\n>135.000 Orang sudah Istiqomah.\n\nAktifkan donasi otomatis setiap setelah subuh?")
            .setPositiveButton("Aktifkan") { _, _ ->
                Toast.makeText(this, "Alhamdulillah! Pahala Subuh Melimpah diaktifkan", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, SedekahActivity::class.java))
            }
            .setNegativeButton("Nanti", null)
            .show()
    }
    
    private fun showDonasiAbadiDialog() {
        AlertDialog.Builder(this)
            .setTitle("🌟 Donasi Abadi (Jariyah)")
            .setMessage("Sedekah Jariyah yang pahalanya terus mengalir meski sudah meninggal.\n\n📚 Pembangunan Masjid\n💧 Sumur Wakaf\n📖 Wakaf Al-Quran\n🏫 Beasiswa Anak Yatim\n\nPilih program wakaf sekarang?")
            .setPositiveButton("Lihat Program") { _, _ ->
                startActivity(Intent(this, KampanyeActivity::class.java))
            }
            .setNegativeButton("Nanti", null)
            .show()
    }
    
    private fun showZakatDialog() {
        AlertDialog.Builder(this)
            .setTitle("Zakat")
            .setMessage("Tunaikan kewajiban zakat Anda:\n\n💰 Zakat Mal (Harta)\n🌾 Zakat Fitrah\n� Zakat Penghasilan\n📊 Kalkulator Zakat\n🕌 Penyaluran Terpercaya\n\nHitung dan bayar zakat sekarang?")
            .setPositiveButton("Bayar Zakat") { _, _ ->
                startActivity(Intent(this, ZakatActivity::class.java))
            }
            .setNegativeButton("Nanti", null)
            .show()
    }    
    private fun showCategoryInfo(category: String, description: String) {
        AlertDialog.Builder(this)
            .setTitle("Kategori: $category")
            .setMessage("$description\n\nLihat kampanye terkait kategori ini?")
            .setPositiveButton("Lihat Kampanye") { _, _ ->
                Toast.makeText(this, "Menampilkan kampanye $category", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, KampanyeActivity::class.java))
            }
            .setNegativeButton("Tutup", null)
            .show()
    }
    
    private fun showIsiSaldoDialog() {
        val dialogView = layoutInflater.inflate(com.example.sedekahyukukom.R.layout.dialog_isi_saldo, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        // Set current saldo
        val tvCurrentSaldo = dialogView.findViewById<android.widget.TextView>(com.example.sedekahyukukom.R.id.tvCurrentSaldo)
        tvCurrentSaldo.text = FormatHelper.formatRupiah(preferenceManager.saldo)

        // Button click handlers
        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btn50k).setOnClickListener {
            addSaldoAndUpdate(50000L)
            dialog.dismiss()
        }

        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btn100k).setOnClickListener {
            addSaldoAndUpdate(100000L)
            dialog.dismiss()
        }

        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btn250k).setOnClickListener {
            addSaldoAndUpdate(250000L)
            dialog.dismiss()
        }

        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btn500k).setOnClickListener {
            addSaldoAndUpdate(500000L)
            dialog.dismiss()
        }

        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btn1jt).setOnClickListener {
            addSaldoAndUpdate(1000000L)
            dialog.dismiss()
        }

        dialogView.findViewById<android.widget.Button>(com.example.sedekahyukukom.R.id.btnBatal).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun addSaldoAndUpdate(amount: Long) {
        preferenceManager.addSaldo(amount)
        loadUserData()
        
        Toast.makeText(
            this,
            "✅ Alhamdulillah! Saldo berhasil ditambah ${FormatHelper.formatRupiah(amount)}\n\n💰 Saldo Baru: ${FormatHelper.formatRupiah(preferenceManager.saldo)}",
            Toast.LENGTH_LONG
        ).show()
    }
}
