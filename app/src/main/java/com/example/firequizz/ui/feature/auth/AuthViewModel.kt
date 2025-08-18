package com.example.firequizz.ui.feature.auth

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firequizz.data.currentUser
import com.example.firequizz.data.currentUser.setUserData
import com.example.firequizz.leaderboard.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class AuthViewModel() : ViewModel() {
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        } else{
            auth.currentUser?.uid?.let {
                db.collection("users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
                    val user = UserModel(
                        name = it["name"].toString(),
                        pic = it["pic"].toString(),
                        score = it["score"].toString().toInt(),
                        id = it["id"].toString()
                    )
                    setUserData(user)
                }
            }
            _authState.value = AuthState.Autheticated
        }
    }

    fun login(email : String, password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Autheticated
                } else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(username: String, email : String, password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val user = UserModel(
                        name = username,
                        pic = "",
                        score = 0,
                        id = auth.currentUser!!.uid
                    )
                    db.collection("users").document(auth.currentUser!!.uid).set(user)
                    setUserData(user)
                    _authState.value = AuthState.Autheticated
                } else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        currentUser.id = ""
        currentUser.name = ""
        currentUser.pic = ""
        currentUser.score = 0
    }


}

sealed class AuthState{
    object Autheticated : AuthState(

    )

    object Unauthenticated : AuthState()

    object Loading : AuthState()

    data class Error(val message : String) : AuthState()
}