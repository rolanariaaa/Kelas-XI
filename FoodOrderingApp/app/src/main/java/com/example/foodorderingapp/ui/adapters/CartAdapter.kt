package com.example.foodorderingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.R
import com.example.foodorderingapp.models.CartItem

class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onRemoveItem: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val quantityText: TextView = itemView.findViewById(R.id.quantityText)
        val itemTotalText: TextView = itemView.findViewById(R.id.itemTotalText)
        val decreaseButton: ImageButton = itemView.findViewById(R.id.decreaseButton)
        val increaseButton: ImageButton = itemView.findViewById(R.id.increaseButton)
        val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        
        holder.foodName.text = cartItem.foodItem.name
        holder.foodPrice.text = "Rp ${cartItem.foodItem.price.toInt()}"
        holder.quantityText.text = cartItem.quantity.toString()
        holder.itemTotalText.text = "Total: Rp ${cartItem.getTotalPrice().toInt()}"
        
        // Load image using Glide or set placeholder
        if (cartItem.foodItem.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(cartItem.foodItem.imageUrl)
                .placeholder(R.drawable.ic_food_placeholder)
                .into(holder.foodImage)
        } else {
            holder.foodImage.setImageResource(R.drawable.ic_food_placeholder)
        }
        
        // Set up click listeners
        holder.decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                onQuantityChanged(cartItem, cartItem.quantity - 1)
            }
        }
        
        holder.increaseButton.setOnClickListener {
            onQuantityChanged(cartItem, cartItem.quantity + 1)
        }
        
        holder.removeButton.setOnClickListener {
            onRemoveItem(cartItem)
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateCartItems(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}
