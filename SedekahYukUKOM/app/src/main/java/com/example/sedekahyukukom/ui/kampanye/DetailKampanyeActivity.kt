package com.example.sedekahyukukom.ui.kampanye

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.databinding.ActivityDetailKampanyeBinding
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager

class DetailKampanyeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKampanyeBinding
    private lateinit var preferenceManager: PreferenceManager
    
    private var kampanyeId = ""
    private var kampanyeJudul = ""
    private var targetDana = 0L
    private var terkumpul = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKampanyeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        loadKampanyeData()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadKampanyeData() {
        kampanyeId = intent.getStringExtra("KAMPANYE_ID") ?: ""
        kampanyeJudul = intent.getStringExtra("KAMPANYE_JUDUL") ?: ""
        val deskripsi = intent.getStringExtra("KAMPANYE_DESKRIPSI") ?: ""
        val kategori = intent.getStringExtra("KAMPANYE_KATEGORI") ?: ""
        targetDana = intent.getLongExtra("KAMPANYE_TARGET", 0L)
        val terkumpulBase = intent.getLongExtra("KAMPANYE_TERKUMPUL", 0L)
        val gambar = intent.getIntExtra("KAMPANYE_GAMBAR", R.drawable.ic_kampanye)

        // Tambahkan donasi yang sudah masuk dari user
        val userDonation = preferenceManager.getKampanyeDonation(kampanyeId)
        terkumpul = terkumpulBase + userDonation

        binding.tvJudul.text = kampanyeJudul
        binding.tvKategori.text = kategori
        binding.tvDeskripsi.text = deskripsi
        binding.tvTarget.text = "Target: ${FormatHelper.formatRupiah(targetDana)}"
        binding.tvTerkumpul.text = "Terkumpul: ${FormatHelper.formatRupiah(terkumpul)}"
        binding.ivKampanye.setImageResource(gambar)
        
        val progress = ((terkumpul.toDouble() / targetDana.toDouble()) * 100).toInt()
        binding.progressBar.progress = progress
        binding.tvProgress.text = "$progress%"
    }

    private fun setupClickListeners() {
        // Animasi pada tombol donasi
        binding.btnDonasi.setOnTouchListener { v, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    v.animate()
                        .scaleX(0.95f)
                        .scaleY(0.95f)
                        .setDuration(100)
                        .start()
                }
                android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                    v.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
            }
            false
        }
        
        binding.btnDonasi.setOnClickListener {
            it.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
            showDonasiDialog()
        }
    }

    private fun showDonasiDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_donasi, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btn5k = dialogView.findViewById<Button>(R.id.btn5k)
        val btn10k = dialogView.findViewById<Button>(R.id.btn10k)
        val btn20k = dialogView.findViewById<Button>(R.id.btn20k)
        val btn50k = dialogView.findViewById<Button>(R.id.btn50k)
        val btnBatal = dialogView.findViewById<Button>(R.id.btnBatal)

        // Animasi scale untuk setiap tombol
        listOf(btn5k, btn10k, btn20k, btn50k, btnBatal).forEach { button ->
            button.setOnTouchListener { v, event ->
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                    }
                    android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                    }
                }
                false
            }
        }

        btn5k.setOnClickListener { processDonasi(5000L); dialog.dismiss() }
        btn10k.setOnClickListener { processDonasi(10000L); dialog.dismiss() }
        btn20k.setOnClickListener { processDonasi(20000L); dialog.dismiss() }
        btn50k.setOnClickListener { processDonasi(50000L); dialog.dismiss() }
        btnBatal.setOnClickListener { dialog.dismiss() }

        dialog.show()
        
        // Animasi masuk dialog
        dialogView.alpha = 0f
        dialogView.scaleX = 0.8f
        dialogView.scaleY = 0.8f
        dialogView.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(200)
            .setInterpolator(android.view.animation.OvershootInterpolator())
            .start()
    }

    private fun processDonasi(nominal: Long) {
        val userId = preferenceManager.userId

        // Validasi saldo cukup
        if (!preferenceManager.deductSaldo(nominal)) {
            Toast.makeText(this, "Saldo tidak cukup! Silakan isi saldo terlebih dahulu.", Toast.LENGTH_LONG).show()
            return
        }

        // Simpan donasi ke kampanye
        preferenceManager.addKampanyeDonation(kampanyeId, nominal)
        
        // Update UI terkumpul dengan animasi
        val oldTerkumpul = terkumpul
        terkumpul += nominal
        animateProgressUpdate(oldTerkumpul, terkumpul)

        // Simpan transaksi ke riwayat lokal
        val riwayat = com.example.sedekahyukukom.model.Riwayat(
            id = System.currentTimeMillis().toString(),
            userId = userId ?: "",
            kampanyeId = kampanyeId,
            kampanyeJudul = kampanyeJudul,
            nominal = nominal,
            metodePembayaran = "Donasi Kampanye",
            tanggal = com.google.firebase.Timestamp(java.util.Date()),
            status = "Berhasil"
        )
        preferenceManager.saveRiwayat(riwayat)

        // Tampilkan animasi success dengan effect
        showSuccessAnimation(nominal)
    }
    
    private fun animateProgressUpdate(from: Long, to: Long) {
        val fromProgress = ((from.toDouble() / targetDana.toDouble()) * 100).toInt()
        val toProgress = ((to.toDouble() / targetDana.toDouble()) * 100).toInt()
        
        // Update langsung tanpa animasi kompleks
        var namaPenggalang = ""
        binding.tvTerkumpul.text = "Terkumpul: ${FormatHelper.formatRupiah(to)}"
        binding.progressBar.progress = toProgress
        binding.tvProgress.text = "$toProgress%"
    }
    
    private fun showSuccessAnimation(nominal: Long) {
        // Haptic feedback
        binding.root.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)
        
        // Toast dengan emoji yang lebih banyak
        Toast.makeText(
            this,
            "✨🎉 Jazakallahu khairan! 🎉✨\n\n💝 Donasi ${FormatHelper.formatRupiah(nominal)} berhasil!\n📈 Terkumpul: ${FormatHelper.formatRupiah(terkumpul)}\n💰 Sisa Saldo: ${FormatHelper.formatRupiah(preferenceManager.saldo)}\n\n🌟 Semoga menjadi berkah!",
            Toast.LENGTH_LONG
        ).show()
        
        // Kembali setelah delay singkat
        binding.root.postDelayed({
            finish()
        }, 2500)
    }
}
