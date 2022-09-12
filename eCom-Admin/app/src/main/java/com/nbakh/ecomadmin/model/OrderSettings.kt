package com.nbakh.ecomadmin.model

data class OrderSettings(
    var deliveryCharge: Int = 0,
    var discount: Int = 0,
    var vat: Int = 0
)
