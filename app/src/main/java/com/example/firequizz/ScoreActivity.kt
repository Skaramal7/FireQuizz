package com.example.firequizz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.firequizz.data.currentUser
import com.example.firequizz.ui.feature.ScoreScreen
import com.example.firequizz.ui.feature.home.HomeActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val score = intent.getIntExtra("Score", 0)

        val db = Firebase.firestore

        val newScore = score + currentUser.score

        db.collection("users").document(currentUser.id).update("score", newScore)
        currentUser.score = newScore

        setContent {
            ScoreScreen(score = score) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}