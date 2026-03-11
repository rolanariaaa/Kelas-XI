package com.example.sedekahyukukom.ui.sedekah

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivitySedekahBinding
import com.example.sedekahyukukom.ui.home.HomeActivity
import com.example.sedekahyukukom.ui.riwayat.RiwayatActivity
import com.example.sedekahyukukom.utils.Constants
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager
import com.example.sedekahyukukom.utils.SuccessAnimationDialog

class SedekahActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySedekahBinding
    private lateinit var preferenceManager: PreferenceManager
    private var selectedNominal = 0L
    private var selectedMetode = ""
    private var selectedTujuan = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySedekahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        setupNominalButtons()
        setupSpinners()
        setupClickListeners()
        startAnimations()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupNominalButtons() {
        // Card sudah memiliki text hardcoded di XML (5K, 10K, 20K, 50K)
        // Jadi kita hanya perlu setup click listeners
        
        binding.btnNominal1.setOnClickListener { selectNominal(Constants.NOMINAL_SEDEKAH[0]) }
        binding.btnNominal2.setOnClickListener { selectNominal(Constants.NOMINAL_SEDEKAH[1]) }
        binding.btnNominal3.setOnClickListener { selectNominal(Constants.NOMINAL_SEDEKAH[2]) }
        binding.btnNominal4.setOnClickListener { selectNominal(Constants.NOMINAL_SEDEKAH[3]) }
    }

    private fun selectNominal(nominal: Long) {
        selectedNominal = nominal
        binding.etCustomNominal.setText("")
        
        // Animasi bounce saat nominal dipilih
        val bounce = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.bounce)
        binding.etCustomNominal.startAnimation(bounce)
    }
    
    private fun startAnimations() {
        // Animasi slide dari kiri untuk nominal buttons
        val slideLeft = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_left)
        binding.btnNominal1.postDelayed({
            binding.btnNominal1.startAnimation(slideLeft)
        }, 100)
        
        binding.btnNominal2.postDelayed({
            binding.btnNominal2.startAnimation(slideLeft)
        }, 150)
        
        // Animasi slide dari kanan untuk nominal buttons
        val slideRight = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_right)
        binding.btnNominal3.postDelayed({
            binding.btnNominal3.startAnimation(slideRight)
        }, 100)
        
        binding.btnNominal4.postDelayed({
            binding.btnNominal4.startAnimation(slideRight)
        }, 150)
        
        // Animasi fade in untuk custom nominal
        val fadeIn = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.fade_in)
        binding.etCustomNominal.postDelayed({
            binding.etCustomNominal.startAnimation(fadeIn)
        }, 200)
        
        // Animasi slide bottom untuk spinners
        val slideBottom = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_bottom)
        binding.spinnerTujuan.postDelayed({
            binding.spinnerTujuan.startAnimation(slideBottom)
        }, 250)
        
        binding.spinnerMetode.postDelayed({
            binding.spinnerMetode.startAnimation(slideBottom)
        }, 300)
        
        // Animasi scale in untuk button sedekah
        val scaleIn = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.scale_in)
        binding.btnSedekah.postDelayed({
            binding.btnSedekah.startAnimation(scaleIn)
        }, 400)
    }

    private fun setupSpinners() {
        // Setup Spinner Tujuan Sedekah
        val tujuanAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.TUJUAN_SEDEKAH
        )
        tujuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTujuan.adapter = tujuanAdapter

        binding.spinnerTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedTujuan = Constants.TUJUAN_SEDEKAH[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        // Setup Spinner Metode Pembayaran
        val metodeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.METODE_PEMBAYARAN
        )
        metodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMetode.adapter = metodeAdapter

        binding.spinnerMetode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedMetode = Constants.METODE_PEMBAYARAN[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupClickListeners() {
        binding.btnSedekah.setOnClickListener {
            val customNominal = binding.etCustomNominal.text.toString()
            val finalNominal = if (customNominal.isNotEmpty()) {
                customNominal.toLongOrNull() ?: 0L
            } else {
                selectedNominal
            }

            if (finalNominal > 0 && selectedMetode.isNotEmpty() && selectedTujuan.isNotEmpty()) {
                showConfirmationDialog(finalNominal)
            } else {
                Toast.makeText(this, "Lengkapi semua pilihan terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showConfirmationDialog(nominal: Long) {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Sedekah")
            .setMessage(
                "Tujuan: $selectedTujuan\n" +
                "Nominal: ${FormatHelper.formatRupiah(nominal)}\n" +
                "Metode: $selectedMetode\n\n" +
                "Apakah Anda yakin?"
            )
            .setPositiveButton("Ya") { _, _ ->
                processSedekah(nominal)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun processSedekah(nominal: Long) {
        binding.btnSedekah.isEnabled = false
        
        // Validasi saldo cukup
        if (!preferenceManager.deductSaldo(nominal)) {
            binding.btnSedekah.isEnabled = true
            Toast.makeText(this, "Saldo tidak cukup! Silakan isi saldo terlebih dahulu.", Toast.LENGTH_LONG).show()
            return
        }
        
        // Simpan transaksi ke riwayat lokal
        val riwayat = com.example.sedekahyukukom.model.Riwayat(
            id = System.currentTimeMillis().toString(),
            userId = preferenceManager.userId ?: "",
            kampanyeId = "",
            kampanyeJudul = selectedTujuan,
            nominal = nominal,
            metodePembayaran = selectedMetode,
            tanggal = com.google.firebase.Timestamp(java.util.Date()),
            status = "Berhasil"
        )
        preferenceManager.saveRiwayat(riwayat)
        
        // Tampilkan animasi success yang keren
        SuccessAnimationDialog(
            context = this,
            amount = FormatHelper.formatRupiah(nominal),
            message = "Alhamdulillah! Sedekah Anda untuk $selectedTujuan\ntelah diterima dengan penuh berkah\n\nSisa Saldo: ${FormatHelper.formatRupiah(preferenceManager.saldo)}",
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