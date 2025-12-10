package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.R
import com.example.foodorderingapp.models.Order
import com.example.foodorderingapp.models.OrderStatus
import java.text.SimpleDateFormat
import java.util.*

class OrderAdapter(
    private var orders: List<Order>,
    private val onTrackOrder: (Order) -> Unit,
    private val onReorder: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orderIdText: TextView = itemView.findViewById(R.id.orderIdText)
        val orderDateText: TextView = itemView.findViewById(R.id.orderDateText)
        val orderStatusText: TextView = itemView.findViewById(R.id.orderStatusText)
        val orderItemsText: TextView = itemView.findViewById(R.id.orderItemsText)
        val orderTotalText: TextView = itemView.findViewById(R.id.orderTotalText)
        val trackOrderButton: Button = itemView.findViewById(R.id.trackOrderButton)
        val reorderButton: Button = itemView.findViewById(R.id.reorderButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        
        holder.orderIdText.text = "Order #${order.id.takeLast(6)}"
        holder.orderDateText.text = formatDate(order.orderDate)
        holder.orderStatusText.text = getStatusText(order.status)
        holder.orderItemsText.text = getOrderItemsText(order)
        holder.orderTotalText.text = "Rp ${order.totalAmount.toInt()}"
        
        // Set status text color based on status
        when (order.status) {
            OrderStatus.PENDING -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.CONFIRMED -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.PREPARING -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.READY -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.OUT_FOR_DELIVERY -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.DELIVERED -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
            OrderStatus.CANCELLED -> {
                holder.orderStatusText.setBackgroundResource(R.drawable.status_background)
                holder.orderStatusText.setTextColor(holder.itemView.context.getColor(android.R.color.white))
            }
        }
        
        // Set up click listeners
        holder.trackOrderButton.setOnClickListener {
            onTrackOrder(order)
        }
        
        holder.reorderButton.setOnClickListener {
            onReorder(order)
        }
    }

    override fun getItemCount(): Int = orders.size

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    private fun getStatusText(status: OrderStatus): String {
        return when (status) {
            OrderStatus.PENDING -> "Pending"
            OrderStatus.CONFIRMED -> "Confirmed"
            OrderStatus.PREPARING -> "Preparing"
            OrderStatus.READY -> "Ready"
            OrderStatus.OUT_FOR_DELIVERY -> "Out for Delivery"
            OrderStatus.DELIVERED -> "Delivered"
            OrderStatus.CANCELLED -> "Cancelled"
        }
    }

    private fun getOrderItemsText(order: Order): String {
        return if (order.items.size <= 2) {
            order.items.joinToString(", ") { "${it.quantity}x ${it.foodItem.name}" }
        } else {
            val firstItem = order.items.first()
            "${firstItem.quantity}x ${firstItem.foodItem.name} + ${order.items.size - 1} more items"
        }
    }
}
