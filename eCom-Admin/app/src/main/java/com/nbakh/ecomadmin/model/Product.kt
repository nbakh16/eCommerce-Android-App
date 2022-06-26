package com.nbakh.ecomadmin.model

data class Product(
    var id: String? = null,
    var name: String? = null,
    var category: String? = null,
    var description: String? = null,
    var salePrice: Double = 0.0,
    var imageUrl: String? = null,
    var isAvailable: Boolean = true
)
