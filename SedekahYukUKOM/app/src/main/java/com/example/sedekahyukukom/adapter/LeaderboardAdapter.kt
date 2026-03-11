package com.example.sedekahyukukom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sedekahyukukom.databinding.ItemLeaderboardBinding
import com.example.sedekahyukukom.model.LeaderboardItem
import com.example.sedekahyukukom.utils.FormatHelper

class LeaderboardAdapter(
    private val items: List<LeaderboardItem>
) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLeaderboardBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLeaderboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        
        with(holder.binding) {
            tvRank.text = "${item.rank}"
            tvBadge.text = item.badge
            tvName.text = item.name
            tvAmount.text = FormatHelper.formatRupiah(item.totalDonasi)
            tvCount.text = "${item.jumlahDonasi} donasi"

            // Highlight top 3
            when (item.rank) {
                1 -> {
                    cardLeaderboard.setCardBackgroundColor(0xFFFFD700.toInt())
                    tvRank.setTextColor(0xFF000000.toInt())
                }
                2 -> {
                    cardLeaderboard.setCardBackgroundColor(0xFFC0C0C0.toInt())
                    tvRank.setTextColor(0xFF000000.toInt())
                }
                3 -> {
                    cardLeaderboard.setCardBackgroundColor(0xFFCD7F32.toInt())
                    tvRank.setTextColor(0xFF000000.toInt())
                }
            }

            // Highlight current user
            if (item.name.contains("Anda") || item.name.contains("Demo")) {
                cardLeaderboard.strokeWidth = 4
                cardLeaderboard.strokeColor = 0xFF00C853.toInt()
            }
        }
    }

    override fun getItemCount() = items.size
}
