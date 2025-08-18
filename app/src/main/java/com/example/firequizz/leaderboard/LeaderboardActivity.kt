package com.example.firequizz.leaderboard

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.firequizz.R
import com.example.firequizz.ui.feature.leaderboard.LeaderboardScreen

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val topUsers = loadData().take(3)
        val otherUsers = loadData().drop(3)

        setContent {
            LeaderboardScreen(topUsers = topUsers, otherUsers = otherUsers, onBackClick = { finish() })
        }
    }

    private fun loadData(): List<UserModel> {
        return listOf(
            UserModel(1, "Sophia", "person1", 4000),
            UserModel(1, "Laura", "person2", 3440),
            UserModel(1, "Chloe", "person3", 5000),
            UserModel(1, "Mary", "person4", 1200),
            UserModel(1, "Olivia", "person5", 50),
            UserModel(1, "Amanda", "person6", 4990),
            UserModel(1, "Joana", "person7", 3540),
        )
    }
}