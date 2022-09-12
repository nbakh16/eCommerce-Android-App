package com.nbakh.ecomadmin.model

data class OrderDetails(
    var productID: String? = null,
    var productName: String? = null,
    var productPrice: Double = 0.0,
    var productQuantity: Double = 0.0,
)
