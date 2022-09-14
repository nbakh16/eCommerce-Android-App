package com.nbakh.ecomuser.utils

const val collectionAdmin = "Admins"
const val collectionProduct = "Products"
const val collectionPurchase = "Purchase"
const val collectionCategory = "Categories"
const val collectionUser = "Users"
const val collectionOrder = "Orders"
const val collectionCart = "CartItems"
const val collectionOrderDetails = "Order Details"
const val collectionOrderSettings = "OrderSettings"
const val documentOrderConstants = "OrderConstants"

class PaymentMethod {
    companion object {
        const val cod = "Cash on Delivery"
        const val online = "Online"
    }
}

class OrderStatus {
    companion object {
        const val pending = "Pending"
        const val delivered = "Delivered"
        const val cancelled = "Cancelled"
    }
}

class CartAction {
    companion object {
        const val addToCart = "Add to Cart"
        const val removeFromCart = "Remove from Cart"
    }
}