package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentSearchBinding
import com.example.foodorderingapp.models.FoodItem
import com.example.foodorderingapp.ui.adapters.FoodAdapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var foodAdapter: FoodAdapter
    private val allFoodItems = mutableListOf<FoodItem>()
    private val filteredFoodItems = mutableListOf<FoodItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        loadFoodItems()
    }

    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        
        // Setup RecyclerView
        foodAdapter = FoodAdapter(filteredFoodItems) { foodItem ->
            // Handle food item click - add to cart or show details
            addToCart(foodItem)
        }
        
        binding.searchResultsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = foodAdapter
        }
    }

    private fun setupListeners() {
        // Search input listener
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterFoodItems()
            }
        })
        
        // Filter chips listeners
        binding.allChip.setOnClickListener { onFilterChipClicked("All") }
        binding.pizzaChip.setOnClickListener { onFilterChipClicked("Pizza") }
        binding.burgerChip.setOnClickListener { onFilterChipClicked("Burger") }
        binding.pastaChip.setOnClickListener { onFilterChipClicked("Pasta") }
        binding.drinksChip.setOnClickListener { onFilterChipClicked("Drinks") }
    }

    private fun onFilterChipClicked(category: String) {
        // Reset all chips
        binding.allChip.isChecked = false
        binding.pizzaChip.isChecked = false
        binding.burgerChip.isChecked = false
        binding.pastaChip.isChecked = false
        binding.drinksChip.isChecked = false
        
        // Check selected chip
        when (category) {
            "All" -> binding.allChip.isChecked = true
            "Pizza" -> binding.pizzaChip.isChecked = true
            "Burger" -> binding.burgerChip.isChecked = true
            "Pasta" -> binding.pastaChip.isChecked = true
            "Drinks" -> binding.drinksChip.isChecked = true
        }
        
        filterFoodItems()
    }

    private fun loadFoodItems() {
        // TODO: Load food items from Firebase
        // For now, using sample data
        val sampleFoods = listOf(
            FoodItem(
                id = "1",
                name = "Margherita Pizza",
                description = "Classic tomato and mozzarella",
                price = 45000.0,
                category = "Pizza"
            ),
            FoodItem(
                id = "2",
                name = "Cheese Burger",
                description = "Juicy beef patty with cheese",
                price = 35000.0,
                category = "Burger"
            ),
            FoodItem(
                id = "3",
                name = "Spaghetti Carbonara",
                description = "Creamy pasta with bacon",
                price = 40000.0,
                category = "Pasta"
            ),
            FoodItem(
                id = "4",
                name = "Coca Cola",
                description = "Refreshing soft drink",
                price = 15000.0,
                category = "Drinks"
            ),
            FoodItem(
                id = "5",
                name = "Pepperoni Pizza",
                description = "Spicy pepperoni with cheese",
                price = 50000.0,
                category = "Pizza"
            ),
            FoodItem(
                id = "6",
                name = "Chicken Burger",
                description = "Grilled chicken with lettuce",
                price = 32000.0,
                category = "Burger"
            ),
            FoodItem(
                id = "7",
                name = "Fettuccine Alfredo",
                description = "Creamy fettuccine pasta",
                price = 42000.0,
                category = "Pasta"
            ),
            FoodItem(
                id = "8",
                name = "Orange Juice",
                description = "Fresh orange juice",
                price = 18000.0,
                category = "Drinks"
            )
        )
        
        allFoodItems.clear()
        allFoodItems.addAll(sampleFoods)
        filterFoodItems()
    }

    private fun filterFoodItems() {
        val searchQuery = binding.searchInput.text.toString().lowercase()
        val selectedCategory = getSelectedCategory()
        
        filteredFoodItems.clear()
        
        allFoodItems.forEach { foodItem ->
            val matchesSearch = searchQuery.isEmpty() || 
                foodItem.name.lowercase().contains(searchQuery) ||
                foodItem.description.lowercase().contains(searchQuery)
            
            val matchesCategory = selectedCategory == "All" || foodItem.category == selectedCategory
            
            if (matchesSearch && matchesCategory) {
                filteredFoodItems.add(foodItem)
            }
        }
        
        foodAdapter = FoodAdapter(filteredFoodItems) { foodItem ->
            addToCart(foodItem)
        }
        binding.searchResultsRecyclerView.adapter = foodAdapter
        
        // Show/hide no results text
        if (filteredFoodItems.isEmpty()) {
            binding.noResultsText.visibility = View.VISIBLE
            binding.searchResultsRecyclerView.visibility = View.GONE
        } else {
            binding.noResultsText.visibility = View.GONE
            binding.searchResultsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun getSelectedCategory(): String {
        return when {
            binding.allChip.isChecked -> "All"
            binding.pizzaChip.isChecked -> "Pizza"
            binding.burgerChip.isChecked -> "Burger"
            binding.pastaChip.isChecked -> "Pasta"
            binding.drinksChip.isChecked -> "Drinks"
            else -> "All"
        }
    }

    private fun addToCart(foodItem: FoodItem) {
        // TODO: Implement add to cart functionality
        android.widget.Toast.makeText(
            requireContext(),
            "${foodItem.name} added to cart!",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
