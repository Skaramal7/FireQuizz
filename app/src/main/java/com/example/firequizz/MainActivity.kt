package com.example.firequizz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.ContextCompat
import com.example.firequizz.Question.Model.QuestionModel
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.Question.QuestionActivity
import com.example.firequizz.leaderboard.LeaderboardActivity
import com.example.firequizz.ui.feature.auth.AuthActivity
import com.example.firequizz.ui.feature.auth.AuthViewModel
import com.example.firequizz.ui.feature.home.MainScreen
import com.google.firebase.database.FirebaseDatabase
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val authViewModel : AuthViewModel by viewModels()

        setContent {
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }
}