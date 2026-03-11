package com.example.sedekahyukukom.ui.zakat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityZakatBinding
import com.example.sedekahyukukom.ui.home.HomeActivity
import com.example.sedekahyukukom.ui.riwayat.RiwayatActivity
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager
import com.example.sedekahyukukom.utils.SuccessAnimationDialog
import java.text.SimpleDateFormat
import java.util.*

class ZakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityZakatBinding
    private lateinit var preferenceManager: PreferenceManager
    private var selectedAmount = 0L
    private var selectedType = "Zakat Mal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZakatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        setupZakatTypes()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupZakatTypes() {
        // Default selected: Zakat Mal
        updateSelectedType("Zakat Mal")
    }

    private fun setupClickListeners() {
        // Zakat Type Selection
        binding.cardZakatMal.setOnClickListener {
            updateSelectedType("Zakat Mal")
        }

        binding.cardZakatFitrah.setOnClickListener {
            updateSelectedType("Zakat Fitrah")
        }

        binding.cardZakatPenghasilan.setOnClickListener {
            updateSelectedType("Zakat Penghasilan")
        }

        // Amount buttons
        binding.btnAmount1.setOnClickListener {
            selectedAmount = 50000
            updateAmountDisplay()
        }

        binding.btnAmount2.setOnClickListener {
            selectedAmount = 100000
            updateAmountDisplay()
        }

        binding.btnAmount3.setOnClickListener {
            selectedAmount = 250000
            updateAmountDisplay()
        }

        binding.btnAmount4.setOnClickListener {
            selectedAmount = 500000
            updateAmountDisplay()
        }

        // Calculator button
        binding.btnKalkulator.setOnClickListener {
            showKalkulatorZakat()
        }

        // Bayar button
        binding.btnBayarZakat.setOnClickListener {
            if (selectedAmount > 0) {
                processZakat()
            } else {
                Toast.makeText(this, "Pilih nominal zakat terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateSelectedType(type: String) {
        selectedType = type
        
        // Reset all cards
        binding.cardZakatMal.strokeWidth = 0
        binding.cardZakatFitrah.strokeWidth = 0
        binding.cardZakatPenghasilan.strokeWidth = 0

        // Highlight selected
        when (type) {
            "Zakat Mal" -> {
                binding.cardZakatMal.strokeWidth = 4
                binding.tvDescription.text = "Zakat dari harta yang tersimpan minimal 1 tahun (nisab 85 gram emas). Zakat Mal sebesar 2.5% dari total harta."
            }
            "Zakat Fitrah" -> {
                binding.cardZakatFitrah.strokeWidth = 4
                binding.tvDescription.text = "Zakat yang wajib dikeluarkan oleh setiap Muslim menjelang Idul Fitri. Minimal 2.5 kg beras atau setara Rp50.000 per jiwa."
                selectedAmount = 50000
                updateAmountDisplay()
            }
            "Zakat Penghasilan" -> {
                binding.cardZakatPenghasilan.strokeWidth = 4
                binding.tvDescription.text = "Zakat dari penghasilan bulanan jika mencapai nisab (setara 85 gram emas). Zakat sebesar 2.5% dari penghasilan."
            }
        }
    }

    private fun updateAmountDisplay() {
        binding.tvTotalAmount.text = FormatHelper.formatRupiah(selectedAmount)
    }

    private fun showKalkulatorZakat() {
        Toast.makeText(this, "Fitur Kalkulator Zakat (Demo Mode)", Toast.LENGTH_SHORT).show()
        
        // Contoh perhitungan sederhana
        when (selectedType) {
            "Zakat Mal" -> {
                // Misal harta Rp 100 juta
                val harta = 100000000L
                val zakat = (harta * 2.5 / 100).toLong()
                selectedAmount = zakat
                updateAmountDisplay()
                Toast.makeText(this, "Contoh: Harta Rp100 juta → Zakat: ${FormatHelper.formatRupiah(zakat)}", Toast.LENGTH_LONG).show()
            }
            "Zakat Fitrah" -> {
                // Default 50rb per jiwa
                selectedAmount = 50000
                updateAmountDisplay()
            }
            "Zakat Penghasilan" -> {
                // Misal gaji Rp 10 juta
                val gaji = 10000000L
                val zakat = (gaji * 2.5 / 100).toLong()
                selectedAmount = zakat
                updateAmountDisplay()
                Toast.makeText(this, "Contoh: Gaji Rp10 juta → Zakat: ${FormatHelper.formatRupiah(zakat)}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun processZakat() {
        val userName = preferenceManager.userName ?: "Demo User"
        val currentDate = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID")).format(Date())

        // Validasi saldo cukup
        if (!preferenceManager.deductSaldo(selectedAmount)) {
            Toast.makeText(this, "Saldo tidak cukup! Silakan isi saldo terlebih dahulu.", Toast.LENGTH_LONG).show()
            return
        }

        // Simpan transaksi ke riwayat lokal
        val riwayat = com.example.sedekahyukukom.model.Riwayat(
            id = System.currentTimeMillis().toString(),
            userId = preferenceManager.userId ?: "",
            kampanyeId = "",
            kampanyeJudul = selectedType,
            nominal = selectedAmount,
            metodePembayaran = "Pembayaran Zakat",
            tanggal = com.google.firebase.Timestamp(Date()),
            status = "Berhasil"
        )
        preferenceManager.saveRiwayat(riwayat)

        // Tampilkan animasi success yang keren
        SuccessAnimationDialog(
            context = this,
            amount = FormatHelper.formatRupiah(selectedAmount),
            message = "Alhamdulillah! $selectedType Anda telah diterima\nSemoga menjadi berkah dan pahala berlimpah\n\nSisa Saldo: ${FormatHelper.formatRupiah(preferenceManager.saldo)}",
            onViewHistory = {
                // Ke halaman riwayat
                startActivity(Intent(this, RiwayatActivity::class.java))
                finish()
            },
            onClose = {
                // Kembali ke home
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        ).show()
    }
}
