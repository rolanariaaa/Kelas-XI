package com.example.sedekahyukukom.ui.dampak

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.databinding.ActivityDampakBinding

class DampakActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDampakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDampakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        loadDampakData()
        startAnimations()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadDampakData() {
        // Data dummy dampak donasi
        // Total statistics
        binding.tvTotalPenerima.text = "12,345"
        binding.tvTotalProgram.text = "89"
        binding.tvTotalRelawan.text = "8,901"
        binding.tvTotalMitra.text = "156"
    }

    private fun startAnimations() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        val slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)

        binding.cardStatistik.startAnimation(fadeIn)
        
        // Animate impact cards with stagger
        binding.cardBencana.postDelayed({ binding.cardBencana.startAnimation(slideInLeft) }, 100)
        binding.cardKesehatan.postDelayed({ binding.cardKesehatan.startAnimation(slideInRight) }, 200)
        binding.cardPendidikan.postDelayed({ binding.cardPendidikan.startAnimation(slideInLeft) }, 300)
        binding.cardPangan.postDelayed({ binding.cardPangan.startAnimation(slideInRight) }, 400)
        
        binding.cardStories.postDelayed({ binding.cardStories.startAnimation(fadeIn) }, 500)
    }
}
