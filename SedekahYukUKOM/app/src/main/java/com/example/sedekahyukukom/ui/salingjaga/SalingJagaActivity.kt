package com.example.sedekahyukukom.ui.salingjaga

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.databinding.ActivitySalingJagaBinding

class SalingJagaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalingJagaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalingJagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupClickListeners()
        startAnimations()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupClickListeners() {
        val buttonClick = AnimationUtils.loadAnimation(this, R.anim.button_click)

        // Program Saling Jaga Cards
        binding.cardBantuanDarurat.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Program Bantuan Darurat - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.cardPendidikanAnak.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Program Pendidikan Anak - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.cardKesehatanGratis.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Program Kesehatan Gratis - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.cardPanganBergizi.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Program Pangan Bergizi - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        // Komunitas Cards
        binding.cardGabungKomunitas.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Gabung Komunitas - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.cardForumDiskusi.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Forum Diskusi - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.cardEventTerdekat.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Event Terdekat - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        // Action Buttons
        binding.btnJadiRelawan.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Pendaftaran Relawan - Segera hadir", Toast.LENGTH_SHORT).show()
        }

        binding.btnAjukanBantuan.setOnClickListener {
            it.startAnimation(buttonClick)
            Toast.makeText(this, "Form Ajukan Bantuan - Segera hadir", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startAnimations() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        val slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)

        binding.cardSummary.startAnimation(fadeIn)
        
        // Animate program cards with stagger
        binding.cardBantuanDarurat.startAnimation(slideInLeft)
        binding.cardPendidikanAnak.startAnimation(slideInRight)
        binding.cardKesehatanGratis.startAnimation(slideInLeft)
        binding.cardPanganBergizi.startAnimation(slideInRight)

        // Animate community cards
        binding.cardGabungKomunitas.startAnimation(slideInLeft)
        binding.cardForumDiskusi.startAnimation(slideInRight)
        binding.cardEventTerdekat.startAnimation(slideInLeft)
    }
}
