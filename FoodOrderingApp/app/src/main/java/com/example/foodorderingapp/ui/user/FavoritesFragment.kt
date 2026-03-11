package com.example.foodorderingapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentFavoritesBinding
import com.example.foodorderingapp.models.FoodItem
import com.example.foodorderingapp.ui.adapters.FavoritesAdapter

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val favoriteItems = mutableListOf<FoodItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        loadFavorites()
    }

    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        
        // Setup RecyclerView
        favoritesAdapter = FavoritesAdapter(
            favoriteItems = favoriteItems,
            onRemoveFavorite = { foodItem ->
                removeFromFavorites(foodItem)
            },
            onAddToCart = { foodItem ->
                addToCart(foodItem)
            }
        )
        
        binding.favoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }
    }

    private fun setupListeners() {
        // Additional listeners if needed
    }

    private fun loadFavorites() {
        // TODO: Load favorites from Firebase or SharedPreferences
        // For now, using sample data
        val sampleFavorites = listOf(
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
                id = "5",
                name = "Pepperoni Pizza",
                description = "Spicy pepperoni with cheese",
                price = 50000.0,
                category = "Pizza"
            )
        )
        
        favoriteItems.clear()
        favoriteItems.addAll(sampleFavorites)
        favoritesAdapter.notifyDataSetChanged()
        
        // Show/hide empty state
        if (favoriteItems.isEmpty()) {
            binding.emptyFavoritesText.visibility = View.VISIBLE
            binding.favoritesRecyclerView.visibility = View.GONE
        } else {
            binding.emptyFavoritesText.visibility = View.GONE
            binding.favoritesRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun removeFromFavorites(foodItem: FoodItem) {
        favoriteItems.removeAll { it.id == foodItem.id }
        favoritesAdapter.notifyDataSetChanged()
        
        // Show/hide empty state
        if (favoriteItems.isEmpty()) {
            binding.emptyFavoritesText.visibility = View.VISIBLE
            binding.favoritesRecyclerView.visibility = View.GONE
        } else {
            binding.emptyFavoritesText.visibility = View.GONE
            binding.favoritesRecyclerView.visibility = View.VISIBLE
        }
        
        Toast.makeText(context, "${foodItem.name} removed from favorites", Toast.LENGTH_SHORT).show()
    }

    private fun addToCart(foodItem: FoodItem) {
        // TODO: Implement add to cart functionality
        Toast.makeText(context, "${foodItem.name} added to cart!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
