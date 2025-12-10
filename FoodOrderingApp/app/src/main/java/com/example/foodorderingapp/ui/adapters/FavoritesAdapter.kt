package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.R
import com.example.foodorderingapp.models.FoodItem

class FavoritesAdapter(
    private val favoriteItems: List<FoodItem>,
    private val onRemoveFavorite: (FoodItem) -> Unit,
    private val onAddToCart: (FoodItem) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val removeFavoriteButton: ImageButton = itemView.findViewById(R.id.removeFavoriteButton)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val foodItem = favoriteItems[position]
        
        holder.foodName.text = foodItem.name
        holder.foodDescription.text = foodItem.description
        holder.foodPrice.text = "Rp ${foodItem.price.toInt()}"
        
        // Load image using Glide or set placeholder
        if (foodItem.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(foodItem.imageUrl)
                .placeholder(R.drawable.ic_food_placeholder)
                .into(holder.foodImage)
        } else {
            holder.foodImage.setImageResource(R.drawable.ic_food_placeholder)
        }
        
        // Set up click listeners
        holder.removeFavoriteButton.setOnClickListener {
            onRemoveFavorite(foodItem)
        }
        
        holder.addToCartButton.setOnClickListener {
            onAddToCart(foodItem)
        }
    }

    override fun getItemCount(): Int = favoriteItems.size
}
