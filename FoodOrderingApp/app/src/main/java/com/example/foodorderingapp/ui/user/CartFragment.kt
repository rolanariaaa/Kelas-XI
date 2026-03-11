package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentCartBinding
import com.example.foodorderingapp.models.CartItem
import com.example.foodorderingapp.ui.adapters.CartAdapter

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var cartAdapter: CartAdapter
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        loadCartItems()
    }

    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        
        // Setup RecyclerView
        cartAdapter = CartAdapter(
            cartItems = cartItems,
            onQuantityChanged = { cartItem, newQuantity ->
                updateCartItemQuantity(cartItem, newQuantity)
            },
            onRemoveItem = { cartItem ->
                removeCartItem(cartItem)
            }
        )
        
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }

    private fun setupListeners() {
        binding.checkoutButton.setOnClickListener {
            // TODO: Navigate to checkout
            showCheckoutDialog()
        }
    }

    private fun loadCartItems() {
        // TODO: Load cart items from SharedPreferences or Firebase
        // For now, using sample data
        val sampleCartItems = listOf(
            CartItem(
                id = "1",
                foodItem = com.example.foodorderingapp.models.FoodItem(
                    id = "1",
                    name = "Margherita Pizza",
                    description = "Classic tomato and mozzarella",
                    price = 45000.0,
                    category = "Pizza"
                ),
                quantity = 2
            ),
            CartItem(
                id = "2",
                foodItem = com.example.foodorderingapp.models.FoodItem(
                    id = "2",
                    name = "Cheese Burger",
                    description = "Juicy beef patty with cheese",
                    price = 35000.0,
                    category = "Burger"
                ),
                quantity = 1
            )
        )
        
        cartItems.clear()
        cartItems.addAll(sampleCartItems)
        cartAdapter.updateCartItems(cartItems)
        updateCartSummary()
    }

    private fun updateCartItemQuantity(cartItem: CartItem, newQuantity: Int) {
        val index = cartItems.indexOfFirst { it.id == cartItem.id }
        if (index != -1) {
            cartItems[index] = cartItem.copy(quantity = newQuantity)
            cartAdapter.updateCartItems(cartItems)
            updateCartSummary()
        }
    }

    private fun removeCartItem(cartItem: CartItem) {
        cartItems.removeAll { it.id == cartItem.id }
        cartAdapter.updateCartItems(cartItems)
        updateCartSummary()
    }

    private fun updateCartSummary() {
        val totalAmount = cartItems.sumOf { it.getTotalPrice() }
        binding.totalAmountText.text = "Rp ${totalAmount.toInt()}"
        
        // Show/hide empty state
        if (cartItems.isEmpty()) {
            binding.emptyCartText.visibility = View.VISIBLE
            binding.cartRecyclerView.visibility = View.GONE
            binding.cartSummaryLayout.visibility = View.GONE
        } else {
            binding.emptyCartText.visibility = View.GONE
            binding.cartRecyclerView.visibility = View.VISIBLE
            binding.cartSummaryLayout.visibility = View.VISIBLE
        }
    }

    private fun showCheckoutDialog() {
        // TODO: Implement checkout dialog
        // For now, just show a simple message
        android.widget.Toast.makeText(
            requireContext(),
            "Checkout feature coming soon!",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
