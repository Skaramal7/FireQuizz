package com.example.firequizz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.firequizz.ui.feature.Dashboard.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        setContent {
            MainScreen(
                onSinglePlayerClick = { /* Handle single player click */ },
                onBoardClick = { /* Handle board click */ }
            )
        }
    }
}