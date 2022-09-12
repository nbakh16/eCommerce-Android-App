package com.nbakh.ecomuser.model

import com.nbakh.ecomuser.utils.OrderStatus
import com.nbakh.ecomuser.utils.PaymentMethod
import java.sql.Timestamp

data class Order(
    var orderID: String? = null,
    var userID: String? = null,
    var orderDate: Timestamp? = null,
    var discount: Int = 0,
    var vat: Int = 0,
    var orderStatus: String = OrderStatus.pending,
    var paymentMethod: String = PaymentMethod.cod,
    var deliveryAddress: String? = null
)
