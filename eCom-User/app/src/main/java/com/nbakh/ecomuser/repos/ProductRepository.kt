package com.nbakh.ecomuser.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.nbakh.ecomuser.model.Product
import com.nbakh.ecomuser.model.Purchase
import com.nbakh.ecomuser.utils.collectionCategory
import com.nbakh.ecomuser.utils.collectionProduct
import com.nbakh.ecomuser.utils.collectionPurchase

class ProductRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getAllProducts() : LiveData<List<Product>> {
        val productLD = MutableLiveData<List<Product>>()
        db.collection(collectionProduct)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val tempList = mutableListOf<Product>()
                for (doc in value!!.documents) {
                    doc.toObject(Product::class.java)?.let { tempList.add(it) }
                }
                productLD.value = tempList
            }
        return productLD
    }

    fun getProductById(id: String) : LiveData<Product> {
        val productLD = MutableLiveData<Product>()
        db.collection(collectionProduct).document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                productLD.value = value?.toObject(Product::class.java)
            }
        return productLD
    }

    fun getAllCategories() : LiveData<List<String>> {
        val catLD = MutableLiveData<List<String>>()
        db.collection(collectionCategory)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val tempList = mutableListOf<String>()
                for (doc in value!!.documents) {
                    tempList.add(doc.get("name").toString())
                }
                catLD.value = tempList
            }
        return catLD
    }
}