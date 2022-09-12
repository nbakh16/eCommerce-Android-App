package com.nbakh.ecomuser.model

data class EComUser(
    var userID: String? = null,
    var userName: String? = null,
    var emailAddress: String? = null,
    var userCreationTimeStamp: Long? = null,
    var userLastSignInTimeStamp: Long? = null,
    var phone: String? = null,
    var address: String? = null,
    var image: String? = null,
    var isOnline: Boolean = false,
    var lastUsageTimestamp: Long? = null,
)
