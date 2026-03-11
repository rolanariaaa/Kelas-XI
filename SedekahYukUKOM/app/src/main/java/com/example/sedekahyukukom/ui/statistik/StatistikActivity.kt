package com.example.sedekahyukukom.ui.statistik

import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.databinding.ActivityStatistikBinding
import com.example.sedekahyukukom.utils.FormatHelper
import com.example.sedekahyukukom.utils.PreferenceManager

class StatistikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatistikBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatistikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        loadStatistik()
        startAnimations()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadStatistik() {
        // Data statistik dummy untuk demo
        val totalDonasi = 350000L
        val totalKampanye = 3
        val bulanIni = 150000L
        val mingguIni = 50000L
        val hariIni = 0L

        // Set data ke UI
        binding.tvTotalDonasi.text = FormatHelper.formatRupiah(totalDonasi)
        binding.tvTotalKampanye.text = "$totalKampanye Kampanye"
        binding.tvBulanIni.text = FormatHelper.formatRupiah(bulanIni)
        binding.tvMingguIni.text = FormatHelper.formatRupiah(mingguIni)
        binding.tvHariIni.text = FormatHelper.formatRupiah(hariIni)

        // Set progress indicators
        binding.progressBulan.progress = calculateProgress(bulanIni, totalDonasi)
        binding.progressMinggu.progress = calculateProgress(mingguIni, bulanIni)
        binding.progressHari.progress = 0

        // Kategori sedekah terbanyak
        binding.tvKategoriTop1.text = "1. Sedekah Umum"
        binding.tvKategoriTop2.text = "2. Bantu Palestina"
        binding.tvKategoriTop3.text = "3. Sedekah Jariyah"

        // Target bulanan
        val target = 500000L
        val progressPercent = ((bulanIni.toFloat() / target) * 100).toInt()
        binding.progressTarget.progress = progressPercent
        binding.tvTargetProgress.text = "$progressPercent% dari target"
        binding.tvTargetAmount.text = "${FormatHelper.formatRupiah(bulanIni)} / ${FormatHelper.formatRupiah(target)}"
    }
    
    private fun startAnimations() {
        // Animasi scale in untuk summary data
        val scaleIn = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.scale_in)
        
        binding.tvTotalDonasi.postDelayed({
            binding.tvTotalDonasi.startAnimation(scaleIn)
        }, 100)
        
        binding.tvTotalKampanye.postDelayed({
            binding.tvTotalKampanye.startAnimation(scaleIn)
        }, 150)
        
        binding.tvBulanIni.postDelayed({
            binding.tvBulanIni.startAnimation(scaleIn)
        }, 200)
        
        binding.tvMingguIni.postDelayed({
            binding.tvMingguIni.startAnimation(scaleIn)
        }, 250)
        
        // Animasi slide dari bawah untuk progress sections
        val slideBottom = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_bottom)
        
        binding.progressBulan.postDelayed({
            binding.progressBulan.startAnimation(slideBottom)
        }, 300)
        
        binding.progressMinggu.postDelayed({
            binding.progressMinggu.startAnimation(slideBottom)
        }, 350)
        
        binding.progressHari.postDelayed({
            binding.progressHari.startAnimation(slideBottom)
        }, 400)
        
        // Animasi fade in untuk kategori dan target
        val fadeIn = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.fade_in)
        
        binding.tvKategoriTop1.postDelayed({
            binding.tvKategoriTop1.startAnimation(fadeIn)
        }, 450)
        
        binding.tvKategoriTop2.postDelayed({
            binding.tvKategoriTop2.startAnimation(fadeIn)
        }, 500)
        
        binding.tvKategoriTop3.postDelayed({
            binding.tvKategoriTop3.startAnimation(fadeIn)
        }, 550)
        
        binding.progressTarget.postDelayed({
            binding.progressTarget.startAnimation(scaleIn)
        }, 600)
    }

    private fun calculateProgress(current: Long, total: Long): Int {
        if (total == 0L) return 0
        return ((current.toFloat() / total) * 100).toInt()
    }
}
