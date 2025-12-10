
package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.databinding.FragmentOrderListBinding
import com.example.foodorderingapp.models.Order
import com.example.foodorderingapp.models.OrderStatus
import com.example.foodorderingapp.ui.adapters.OrderAdapter

class OrderListFragment : Fragment() {
    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    
    private var orderAdapter: OrderAdapter? = null
    private var orderStatus: OrderStatus? = null

    companion object {
        private const val ARG_ORDER_STATUS = "order_status"
        
        fun newInstance(status: OrderStatus?) = OrderListFragment().apply {
            arguments = Bundle().apply {
                if (status != null) {
                    putSerializable(ARG_ORDER_STATUS, status as java.io.Serializable)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("DEPRECATION")
            orderStatus = it.getSerializable(ARG_ORDER_STATUS) as? OrderStatus
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadOrders()
    }

    private fun setupRecyclerView() {
        try {
            orderAdapter = OrderAdapter(
                orders = emptyList(),
                onTrackOrder = { order -> handleTrackOrder(order) },
                onReorder = { order -> handleReorder(order) }
            )
            
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = orderAdapter
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showMessage("Error setting up orders list")
        }
    }

    private fun loadOrders() {
        try {
            val sampleOrders = listOf(
                Order(
                    id = "ORD001",
                    userId = "user1",
                    restaurantId = "rest1",
                    totalAmount = 125000.0,
                    status = OrderStatus.PENDING,
                    orderDate = System.currentTimeMillis() - 86400000,
                    deliveryAddress = "123 Main St, City"
                ),
                Order(
                    id = "ORD002",
                    userId = "user1",
                    restaurantId = "rest1",
                    totalAmount = 85000.0,
                    status = OrderStatus.DELIVERED,
                    orderDate = System.currentTimeMillis() - 172800000,
                    deliveryAddress = "123 Main St, City"
                ),
                Order(
                    id = "ORD003",
                    userId = "user1",
                    restaurantId = "rest1",
                    totalAmount = 95000.0,
                    status = OrderStatus.OUT_FOR_DELIVERY,
                    orderDate = System.currentTimeMillis() - 3600000,
                    deliveryAddress = "123 Main St, City"
                )
            )

            val filteredOrders = if (orderStatus != null) {
                sampleOrders.filter { it.status == orderStatus }
            } else {
                sampleOrders
            }

            orderAdapter?.updateOrders(filteredOrders)
            updateEmptyState(filteredOrders.isEmpty())
        } catch (e: Exception) {
            e.printStackTrace()
            showMessage("Error loading orders")
        }
    }

    private fun handleTrackOrder(order: Order) {
        try {
            showMessage("Tracking order ${order.id}")
        } catch (e: Exception) {
            e.printStackTrace()
            showMessage("Error tracking order")
        }
    }

    private fun handleReorder(order: Order) {
        try {
            showMessage("Reordering items from order ${order.id}")
        } catch (e: Exception) {
            e.printStackTrace()
            showMessage("Error reordering items")
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun showMessage(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        orderAdapter = null
        _binding = null
    }
}