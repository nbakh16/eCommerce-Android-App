package com.nbakh.ecomuser.model

import com.google.firebase.Timestamp

data class Purchase(
    var purchaseId: String? = null,
    var productId: String? = null,
    var purchaseDate: Timestamp? = null,
    var purchasePrice: Double = 0.0,
    var quantity: Double = 1.0
)