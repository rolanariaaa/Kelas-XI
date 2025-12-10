package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentNotificationsBinding
import com.example.foodorderingapp.models.Notification
import com.example.foodorderingapp.models.NotificationType
import com.example.foodorderingapp.ui.adapters.NotificationAdapter

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var notificationAdapter: NotificationAdapter
    private val notifications = mutableListOf<Notification>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        loadNotifications()
    }

    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        
        // Setup RecyclerView
        notificationAdapter = NotificationAdapter(
            notifications = notifications,
            onNotificationClick = { notification ->
                onNotificationClick(notification)
            }
        )
        
        binding.notificationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notificationAdapter
        }
    }

    private fun setupListeners() {
        // Additional listeners if needed
    }

    private fun loadNotifications() {
        // TODO: Load notifications from Firebase
        // For now, using sample data
        val sampleNotifications = listOf(
            Notification(
                id = "1",
                title = "Order Confirmed",
                message = "Your order #12345 has been confirmed and is being prepared.",
                type = NotificationType.ORDER,
                isRead = false,
                orderId = "12345"
            ),
            Notification(
                id = "2",
                title = "Order Ready",
                message = "Your order #12344 is ready for pickup!",
                type = NotificationType.ORDER,
                isRead = false,
                orderId = "12344"
            ),
            Notification(
                id = "3",
                title = "Special Offer",
                message = "Get 20% off on all pizzas this weekend!",
                type = NotificationType.PROMOTION,
                isRead = true
            ),
            Notification(
                id = "4",
                title = "Welcome!",
                message = "Welcome to Food Ordering App! Enjoy your first order.",
                type = NotificationType.GENERAL,
                isRead = true
            ),
            Notification(
                id = "5",
                title = "Order Delivered",
                message = "Your order #12343 has been delivered. Enjoy your meal!",
                type = NotificationType.ORDER,
                isRead = true,
                orderId = "12343"
            )
        )
        
        notifications.clear()
        notifications.addAll(sampleNotifications)
        notificationAdapter.notifyDataSetChanged()
        
        // Show/hide empty state
        if (notifications.isEmpty()) {
            binding.emptyNotificationsText.visibility = View.VISIBLE
            binding.notificationsRecyclerView.visibility = View.GONE
        } else {
            binding.emptyNotificationsText.visibility = View.GONE
            binding.notificationsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun onNotificationClick(notification: Notification) {
        // Mark as read
        val index = notifications.indexOfFirst { it.id == notification.id }
        if (index != -1) {
            notifications[index] = notification.copy(isRead = true)
            notificationAdapter.notifyItemChanged(index)
        }
        
        // Handle notification click based on type
        when (notification.type) {
            NotificationType.ORDER -> {
                // Navigate to order details
                Toast.makeText(context, "Opening order ${notification.orderId}", Toast.LENGTH_SHORT).show()
            }
            NotificationType.PROMOTION -> {
                // Navigate to promotions
                Toast.makeText(context, "Opening promotions", Toast.LENGTH_SHORT).show()
            }
            NotificationType.GENERAL -> {
                // Show general notification details
                Toast.makeText(context, "General notification", Toast.LENGTH_SHORT).show()
            }
            NotificationType.SYSTEM -> {
                // Navigate to settings
                Toast.makeText(context, "Opening settings", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
