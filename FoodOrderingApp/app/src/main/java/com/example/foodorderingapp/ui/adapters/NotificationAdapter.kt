package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.R
import com.example.foodorderingapp.models.Notification
import java.text.SimpleDateFormat
import java.util.*

class NotificationAdapter(
    private val notifications: List<Notification>,
    private val onNotificationClick: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notificationIcon: ImageView = itemView.findViewById(R.id.notificationIcon)
        val notificationTitle: TextView = itemView.findViewById(R.id.notificationTitle)
        val notificationMessage: TextView = itemView.findViewById(R.id.notificationMessage)
        val notificationTime: TextView = itemView.findViewById(R.id.notificationTime)
        val unreadIndicator: View = itemView.findViewById(R.id.unreadIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        
        holder.notificationTitle.text = notification.title
        holder.notificationMessage.text = notification.message
        holder.notificationTime.text = formatTime(notification.createdAt)
        
        // Set icon based on notification type
        val iconRes = when (notification.type) {
            com.example.foodorderingapp.models.NotificationType.ORDER -> R.drawable.ic_orders
            com.example.foodorderingapp.models.NotificationType.PROMOTION -> R.drawable.ic_favorite
            com.example.foodorderingapp.models.NotificationType.GENERAL -> R.drawable.ic_notifications
            com.example.foodorderingapp.models.NotificationType.SYSTEM -> R.drawable.ic_settings
        }
        holder.notificationIcon.setImageResource(iconRes)
        
        // Show/hide unread indicator
        holder.unreadIndicator.visibility = if (notification.isRead) View.GONE else View.VISIBLE
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onNotificationClick(notification)
        }
    }

    override fun getItemCount(): Int = notifications.size

    private fun formatTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        return when {
            diff < 60000 -> "Just now"
            diff < 3600000 -> "${diff / 60000} min ago"
            diff < 86400000 -> "${diff / 3600000} hour ago"
            else -> {
                val sdf = SimpleDateFormat("MMM dd", Locale.getDefault())
                sdf.format(Date(timestamp))
            }
        }
    }
}
