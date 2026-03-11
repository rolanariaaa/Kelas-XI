package com.example.sedekahyukukom.ui.leaderboard

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedekahyukukom.adapter.LeaderboardAdapter
import com.example.sedekahyukukom.databinding.ActivityLeaderboardBinding
import com.example.sedekahyukukom.model.LeaderboardItem

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderboardBinding
    private lateinit var adapter: LeaderboardAdapter
    private val leaderboardList = mutableListOf<LeaderboardItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadLeaderboard()
        startAnimations()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = LeaderboardAdapter(leaderboardList)
        binding.rvLeaderboard.layoutManager = LinearLayoutManager(this)
        binding.rvLeaderboard.adapter = adapter
    }

    private fun loadLeaderboard() {
        // Data dummy leaderboard
        leaderboardList.clear()
        leaderboardList.addAll(
            listOf(
                LeaderboardItem(1, "Ahmad Fauzi", 5000000L, 50, "🥇"),
                LeaderboardItem(2, "Siti Nurhaliza", 3500000L, 42, "🥈"),
                LeaderboardItem(3, "Budi Santoso", 2800000L, 35, "🥉"),
                LeaderboardItem(4, "Dewi Lestari", 2200000L, 28, "⭐"),
                LeaderboardItem(5, "Rizki Ramadhan", 1900000L, 25, "⭐"),
                LeaderboardItem(6, "Putri Ayu", 1500000L, 20, "⭐"),
                LeaderboardItem(7, "Hendra Wijaya", 1200000L, 18, "⭐"),
                LeaderboardItem(8, "Anda (Demo User)", 350000L, 3, "⭐"),
                LeaderboardItem(9, "Faisal Abdullah", 200000L, 2, "⭐"),
                LeaderboardItem(10, "Nina Sari", 100000L, 1, "⭐")
            )
        )
        adapter.notifyDataSetChanged()
    }
    
    private fun startAnimations() {
        // Animasi slide bottom untuk RecyclerView
        val slideBottom = AnimationUtils.loadAnimation(this, com.example.sedekahyukukom.R.anim.slide_in_bottom)
        binding.rvLeaderboard.postDelayed({
            binding.rvLeaderboard.startAnimation(slideBottom)
        }, 200)
    }
}
