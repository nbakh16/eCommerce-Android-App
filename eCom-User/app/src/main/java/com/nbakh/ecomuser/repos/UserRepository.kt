package com.nbakh.ecomuser.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.nbakh.ecomuser.model.CartItem
import com.nbakh.ecomuser.model.EComUser
import com.nbakh.ecomuser.utils.collectionCart
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

    fun addToCart(userId: String, cartItem: CartItem) {
        val cartDocRef = db.collection(collectionUser).document(userId)
            .collection(collectionCart).document(cartItem.productId!!)
        cartDocRef.set(cartItem)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun removeFromCart(userId: String, productId: String) {
        db.collection(collectionUser).document(userId)
            .collection(collectionCart).document(productId).delete()
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun getAllCartItems(userId: String) : LiveData<List<CartItem>> {
        val cartLD = MutableLiveData<List<CartItem>>()
        db.collection(collectionUser).document(userId)
            .collection(collectionCart)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val tempList = mutableListOf<CartItem>()
                for (doc in value!!.documents) {
                    doc.toObject(CartItem::class.java)?.let { tempList.add(it) }
                }
                cartLD.value = tempList
            }
        return cartLD
    }

    fun updateLastSignInTimeAndOnlineStatus(userId: String, time: Long) {
        db.collection(collectionUser).document(userId)
            .update(mapOf("userLastSignInTimeStamp" to time, "online" to true))
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun updateLastAppExitTimeAndOnlineStatus(time: Long, userId: String, status: Boolean, callback: (() -> Unit)? = null) {
        db.collection(collectionUser).document(userId)
            .update(mapOf("lastUsageTimestamp" to time, "online" to status))
            .addOnSuccessListener {
                callback?.invoke()
            }.addOnFailureListener {

            }
    }

    fun updateOnlineStatus(userId: String, status: Boolean, callback: (() -> Unit)? = null) {
        db.collection(collectionUser).document(userId)
            .update("online", status)
            .addOnSuccessListener {
                callback?.let { it1 -> it1() }
            }.addOnFailureListener {

            }
    }
}