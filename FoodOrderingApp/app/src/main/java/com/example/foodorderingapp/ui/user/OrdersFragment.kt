package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodorderingapp.databinding.FragmentOrdersBinding
import com.example.foodorderingapp.models.OrderStatus
import com.google.android.material.tabs.TabLayoutMediator

class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        try {
            // Setup toolbar
            binding.toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
            
            // Setup ViewPager2 with adapter
            binding.ordersViewPager.adapter = OrderPagerAdapter(this)
            
            // Setup TabLayout with ViewPager2
            TabLayoutMediator(binding.orderTabs, binding.ordersViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "All"
                    1 -> "Pending"
                    2 -> "Processing"
                    3 -> "Completed"
                    else -> ""
                }
            }.attach()
            
        } catch (e: Exception) {
            e.printStackTrace()
            context?.let {
                Toast.makeText(it, "Error setting up UI", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private inner class OrderPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 4 // All, Pending, Processing, Completed
        
        override fun createFragment(position: Int): Fragment {
            val status = when (position) {
                0 -> null // All orders
                1 -> OrderStatus.PENDING
                2 -> OrderStatus.OUT_FOR_DELIVERY
                3 -> OrderStatus.DELIVERED
                else -> null
            }
            return OrderListFragment.newInstance(status)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
