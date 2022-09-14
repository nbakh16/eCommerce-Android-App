package com.nbakh.ecomuser.viewmodel

import androidx.lifecycle.ViewModel
import com.nbakh.ecomuser.model.CartItem
import com.nbakh.ecomuser.repos.UserRepository

class UserViewModel : ViewModel() {
    val userRepository = UserRepository()

    fun getCartItems(userId: String) = userRepository.getAllCartItems(userId)
    fun addToCart(userId: String, cartItem: CartItem) = userRepository.addToCart(userId, cartItem)
    fun removeFromCart(userId: String, productId: String) = userRepository.removeFromCart(userId, productId)
}