package com.example.firequizz.ui.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firequizz.R
import com.example.firequizz.ui.feature.auth.AuthActivity
import com.example.firequizz.ui.feature.auth.AuthViewModel

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val authViewModel = AuthViewModel()

        setContent {
            ProfileScreen(
                authViewModel,
                onSignOut = {
                    authViewModel.signout()
                    startActivity(Intent(this, AuthActivity::class.java))
                }
            )
        }
    }
}