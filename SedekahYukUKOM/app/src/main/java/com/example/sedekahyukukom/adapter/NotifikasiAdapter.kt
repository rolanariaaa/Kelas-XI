package com.example.sedekahyukukom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sedekahyukukom.databinding.ItemNotifikasiBinding
import com.example.sedekahyukukom.model.Notifikasi
import com.example.sedekahyukukom.utils.FormatHelper
import java.text.SimpleDateFormat
import java.util.Locale

class NotifikasiAdapter(
    private val items: List<Notifikasi>
) : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNotifikasiBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotifikasiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        
        with(holder.binding) {
            tvTitle.text = item.title
            tvMessage.text = item.message
            
            val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
            tvTimestamp.text = sdf.format(item.timestamp)

            // Set background based on read status
            if (!item.isRead) {
                cardNotifikasi.setCardBackgroundColor(0xFFE8F5E9.toInt())
                tvTitle.setTextColor(0xFF00C853.toInt())
            } else {
                cardNotifikasi.setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            // Set icon based on type
            val icon = when (item.type) {
                "success" -> "✅"
                "info" -> "ℹ️"
                "warning" -> "⚠️"
                "achievement" -> "🏆"
                else -> "📬"
            }
            tvIcon.text = icon
        }
    }

    override fun getItemCount() = items.size
}
