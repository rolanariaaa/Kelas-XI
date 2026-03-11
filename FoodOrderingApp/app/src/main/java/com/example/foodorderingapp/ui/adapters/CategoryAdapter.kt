package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.R

data class FoodCategory(
    val name: String,
    val isSelected: Boolean = false
)

class CategoryAdapter(
    private val categories: List<FoodCategory>,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        
        holder.categoryName.text = category.name
        holder.categoryName.isSelected = category.isSelected
        
        holder.itemView.setOnClickListener {
            onCategoryClick(position)
        }
    }

    override fun getItemCount(): Int = categories.size
}

