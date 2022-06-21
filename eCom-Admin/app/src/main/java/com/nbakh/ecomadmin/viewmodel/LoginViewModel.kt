package com.nbakh.ecomadmin.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nbakh.ecomadmin.LoginFragment
import com.nbakh.ecomadmin.utils.collectionAdmin

class LoginViewModel : ViewModel() {
    enum class AuthState {
        AUTHENTICATED, UNAUTHENTICATED
    }
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val authStateLD: MutableLiveData<AuthState> = MutableLiveData()
    val errMsgLD: MutableLiveData<String> = MutableLiveData()

    init {
        if (firebaseAuth.currentUser != null) {
            authStateLD.value = AuthState.AUTHENTICATED
        } else {
            authStateLD.value = AuthState.UNAUTHENTICATED
        }
    }

    fun loginAdmin(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                val uid = it.user!!.uid
                db.collection(collectionAdmin).document(uid).get()
                    .addOnSuccessListener {
                        if (it.exists()) {
                            authStateLD.value = AuthState.AUTHENTICATED
                        } else {
                            errMsgLD.value = "Please login with an Admin account!"
                            firebaseAuth.signOut()
                        }
                    }
            }
            .addOnFailureListener {
                errMsgLD.value = it.localizedMessage
            }
    }

    fun logout(){
        firebaseAuth.signOut()
        authStateLD.value = AuthState.UNAUTHENTICATED
    }
}