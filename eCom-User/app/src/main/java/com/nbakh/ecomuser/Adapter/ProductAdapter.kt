package com.nbakh.ecomuser.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nbakh.ecomuser.databinding.ProductItemRowBinding
import com.nbakh.ecomuser.model.CartItem
import com.nbakh.ecomuser.model.Product
import com.nbakh.ecomuser.utils.CartAction

class ProductAdapter(val cartBtnAction: (String, CartItem) -> Unit) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtil()){

    class ProductViewHolder(val binding: ProductItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        if (product.inCart) {
            holder.binding.productRowCartBtn.text = CartAction.removeFromCart
        } else {
            holder.binding.productRowCartBtn.text = CartAction.addToCart
        }
        holder.bind(product)
        holder.itemView.setOnClickListener {

        }
        holder.binding.productRowCartBtn.setOnClickListener {
            val cartItem = CartItem(
                productId = product.id,
                productName = product.name,
                price = product.salePrice
            )
            val action = if (product.inCart) CartAction.removeFromCart else CartAction.addToCart
            cartBtnAction(action, cartItem)
        }
    }
}