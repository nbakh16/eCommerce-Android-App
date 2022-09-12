package com.nbakh.ecomuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nbakh.ecomuser.model.EComUser
import com.nbakh.ecomuser.repos.UserRepository

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

    fun loginUser(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                UserRepository().updateLastSignInTimeAndOnlineStatus(
                    userId = firebaseAuth.currentUser?.uid!!,
                    time = firebaseAuth.currentUser?.metadata?.lastSignInTimestamp!!
                )
                authStateLD.value = AuthState.AUTHENTICATED
            }.addOnFailureListener {
                errMsgLD.value = it.localizedMessage
            }
    }

    fun registerUser(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                val ecomUser = EComUser(
                    userID = firebaseAuth.currentUser?.uid,
                    emailAddress = firebaseAuth.currentUser?.email,
                    userCreationTimeStamp = firebaseAuth.currentUser?.metadata?.creationTimestamp,
                    userLastSignInTimeStamp = firebaseAuth.currentUser?.metadata?.lastSignInTimestamp,
                    isOnline = true
                )
                UserRepository().insertNewUser(ecomUser)
                authStateLD.value = AuthState.AUTHENTICATED
            }.addOnFailureListener {
                errMsgLD.value = it.localizedMessage
            }
    }

    fun updateLastAppExitTimeAndOnlineStatus(time: Long, status: Boolean) {
        UserRepository().updateLastAppExitTimeAndOnlineStatus(status = status, time = time, userId = firebaseAuth.currentUser!!.uid)
    }

    fun updateOnlineStatus(status: Boolean) {
        UserRepository().updateOnlineStatus(firebaseAuth.currentUser!!.uid, status)
    }

    fun logout() {
        firebaseAuth.currentUser?.let {
            val userId = it.uid
            UserRepository().updateLastAppExitTimeAndOnlineStatus(
                time = System.currentTimeMillis(),
                userId = userId,
                status = false
            ) {
                firebaseAuth.signOut()
                authStateLD.value = AuthState.UNAUTHENTICATED
            }
        }
    }
}