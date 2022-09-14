package com.nbakh.ecomuser.model

data class Product(
    var id: String? = null,
    var name: String? = null,
    var category: String? = null,
    var description: String? = null,
    var salePrice: Double = 0.0,
    var imageUrl: String? = null,
    var available: Boolean = true,
    var inCart: Boolean = false
)
