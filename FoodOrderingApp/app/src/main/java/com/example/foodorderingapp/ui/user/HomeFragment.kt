    package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentHomeBinding
import com.example.foodorderingapp.models.FoodItem
import com.example.foodorderingapp.ui.adapters.CategoryAdapter
import com.example.foodorderingapp.ui.adapters.FoodAdapter
import com.example.foodorderingapp.ui.adapters.FoodCategory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var foodAdapter: FoodAdapter
    private var selectedCategoryIndex = 0
    private var allFoods: List<FoodItem> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            setupUI()
            setupListeners()
            loadSampleData()
        } catch (e: Exception) {
            e.printStackTrace()
            context?.let {
                android.widget.Toast.makeText(
                    it,
                    "Terjadi kesalahan saat memuat data",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupUI() {
        // Setup categories RecyclerView
        val categories = listOf(
            FoodCategory("All", true),
            FoodCategory("Pizza"),
            FoodCategory("Burger"),
            FoodCategory("Pasta"),
            FoodCategory("Drinks")
        )
        
        categoryAdapter = CategoryAdapter(categories) { position ->
            selectedCategoryIndex = position
            categoryAdapter.notifyDataSetChanged()
            // Filter foods based on selected category
            filterFoodsByCategory(categories[position].name)
        }
        
        binding.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
        
        // Setup foods RecyclerView
        foodAdapter = FoodAdapter(emptyList()) { foodItem ->
            // Handle food item click - add to cart
            addToCart(foodItem)
        }
        
        binding.foodsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = foodAdapter
        }
    }

    private fun setupListeners() {
        try {
            binding.cartButton?.setOnClickListener {
                // TODO: Navigate to cart
                // findNavController().navigate(R.id.action_home_to_cart)
            }
            
            binding.profileButton?.setOnClickListener {
                // TODO: Navigate to profile
                // findNavController().navigate(R.id.action_home_to_profile)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun loadSampleData() {
        allFoods = listOf(
            FoodItem(
                id = "1",
                name = "Margherita Pizza",
                description = "Classic tomato and mozzarella",
                price = 45000.0,
                category = "Pizza",
                imageUrl = "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            ),
            FoodItem(
                id = "2",
                name = "Cheese Burger",
                description = "Juicy beef patty with cheese",
                price = 35000.0,
                category = "Burger",
                imageUrl = "https://images.unsplash.com/photo-1571091718767-18b5b1457add?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            ),
            FoodItem(
                id = "3",
                name = "Spaghetti Carbonara",
                description = "Creamy pasta with bacon",
                price = 40000.0,
                category = "Pasta",
                imageUrl = "https://images.unsplash.com/photo-1612874742237-6526221588e3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            ),
            FoodItem(
                id = "4",
                name = "Coca Cola",
                description = "Refreshing soft drink",
                price = 15000.0,
                category = "Drinks",
                imageUrl = "https://images.unsplash.com/photo-1629203851122-3726ecdf080e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            ),
            FoodItem(
                id = "5",
                name = "Pepperoni Pizza",
                description = "Spicy pepperoni with cheese",
                price = 50000.0,
                category = "Pizza",
                imageUrl = "https://images.unsplash.com/photo-1628840042765-356cda07504e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            ),
            FoodItem(
                id = "6",
                name = "Chicken Burger",
                description = "Grilled chicken with lettuce",
                price = 32000.0,
                category = "Burger",
                imageUrl = "https://images.unsplash.com/photo-1615297928064-24977384d0da?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            )
        )
        
        updateFoodList(allFoods)
    }
    
    private fun filterFoodsByCategory(category: String) {
        try {
            val filteredFoods = if (category == "All") {
                allFoods
            } else {
                allFoods.filter { it.category == category }
            }
            updateFoodList(filteredFoods)
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Gagal memfilter makanan")
        }
    }

    private fun updateFoodList(foods: List<FoodItem>) {
        foodAdapter = FoodAdapter(foods) { foodItem ->
            addToCart(foodItem)
        }
        binding.foodsRecyclerView.adapter = foodAdapter
    }

    private fun addToCart(foodItem: FoodItem) {
        try {
            // TODO: Implement add to cart functionality
            showToast("${foodItem.name} added to cart!")
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Gagal menambahkan ke keranjang")
        }
    }

    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
