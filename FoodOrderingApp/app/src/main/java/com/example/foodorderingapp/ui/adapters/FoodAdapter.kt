package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.R
import com.example.foodorderingapp.models.FoodItem

class FoodAdapter(
    private val foodList: List<FoodItem>,
    private val onItemClick: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        
        holder.foodName.text = food.name
        holder.foodPrice.text = "Rp ${food.price.toInt()}"
        holder.foodDescription.text = food.description
        
        // Load image using Glide or set placeholder
        if (food.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(food.imageUrl)
                .placeholder(R.drawable.ic_food_placeholder)
                .into(holder.foodImage)
        } else {
            holder.foodImage.setImageResource(R.drawable.ic_food_placeholder)
        }
        
        holder.itemView.setOnClickListener {
            onItemClick(food)
        }
    }

    override fun getItemCount(): Int = foodList.size
}

