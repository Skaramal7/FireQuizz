package com.example.firequizz.Question

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firequizz.Question.Model.QuestionModel
import com.example.firequizz.R
import com.example.firequizz.ScoreActivity
import com.example.firequizz.ui.feature.question.QuestionScreen

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")?: arrayListOf()

        setContent {
            QuestionScreen(
                questions = receivedList,
                onBackClick = {finish()},
                onFinish = {
                    finalScore -> val intent = Intent(this, ScoreActivity::class.java)
                    intent.putExtra("Score", finalScore)
                    startActivity(intent)
                    finish()
                }
            )
        }
    }
}