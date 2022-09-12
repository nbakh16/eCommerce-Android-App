package com.nbakh.ecomuser.viewmodel


import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.nbakh.ecomuser.model.Product
import com.nbakh.ecomuser.model.Purchase
import com.nbakh.ecomuser.repos.ProductRepository
import java.io.ByteArrayOutputStream

class ProductViewModel : ViewModel() {
    val repository = ProductRepository()
    val productListLD: MutableLiveData<List<Product>> = MutableLiveData()
    val purchaseListLD: MutableLiveData<List<Purchase>> = MutableLiveData()
    val errMsgLD = MutableLiveData<String>()
    val statusLD = MutableLiveData<String>()

    fun getAllCategories() : LiveData<List<String>>{
        return repository.getAllCategories()
    }

    fun uploadImage(bitmap: Bitmap, callback: (String) -> Unit) {
        val photoRef = FirebaseStorage.getInstance().reference
            .child("images/${System.currentTimeMillis()}")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val data: ByteArray = baos.toByteArray()
        val uploadTask = photoRef.putBytes(data)
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                callback(downloadUri)
            } else {
                errMsgLD.value = "Upload failed, Please check your internet connection!"
            }
        }
    }

    fun addNewProduct(product: Product, purchase: Purchase, callback: (String) -> Unit) {
        repository.addNewProduct(product, purchase, callback)
    }

    fun addRepurchase(purchase: Purchase) = repository.addRePurchase(purchase)
    fun getProducts() = repository.getAllProducts()
    fun getProductById(id:String) = repository.getProductById(id)
    fun getPurchaseListByProductId(id:String) = repository.getPurchaseByProductId(id)
}