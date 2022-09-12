package com.nbakh.ecomuser.repos

import com.google.firebase.firestore.FirebaseFirestore
import com.nbakh.ecomuser.model.EComUser
import com.nbakh.ecomuser.utils.collectionUser

class UserRepository {
    val db = FirebaseFirestore.getInstance()

    fun insertNewUser(ecomUser: EComUser) {
        db.collection(collectionUser).document(ecomUser.userID!!)
            .set(ecomUser)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun updateLastSignInTimeAndOnlineStatus(userId: String, time: Long) {
        db.collection(collectionUser).document(userId)
            .update(mapOf("userLastSignInTimeStamp" to time, "isOnline" to true))
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun updateLastAppExitTimeAndOnlineStatus(time: Long, userId: String, status: Boolean, callback: (() -> Unit)? = null) {
        db.collection(collectionUser).document(userId)
            .update(mapOf("lastUsageTimestamp" to time, "isOnline" to status))
            .addOnSuccessListener {
                callback?.invoke()
            }.addOnFailureListener {

            }
    }

    fun updateOnlineStatus(userId: String, status: Boolean, callback: (() -> Unit)? = null) {
        db.collection(collectionUser).document(userId)
            .update("isOnline", status)
            .addOnSuccessListener {
                callback?.let { it1 -> it1() }
            }.addOnFailureListener {

            }
    }
}