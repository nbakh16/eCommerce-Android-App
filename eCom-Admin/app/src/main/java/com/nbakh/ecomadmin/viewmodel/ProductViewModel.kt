package com.nbakh.ecomadmin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nbakh.ecomadmin.model.Product
import com.nbakh.ecomadmin.model.Purchase
import com.nbakh.ecomadmin.repos.ProductRepository

class ProductViewModel : ViewModel() {
    val repository = ProductRepository()
    val productListLD: MutableLiveData<List<Product>> = MutableLiveData()
    val purchaseListLD: MutableLiveData<List<Purchase>> = MutableLiveData()

    fun getAllCategories() : LiveData<List<String>>{
        return repository.getAllCategories()
    }
}