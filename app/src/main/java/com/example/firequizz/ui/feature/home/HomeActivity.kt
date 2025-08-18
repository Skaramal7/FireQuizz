package com.example.firequizz.ui.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.ContextCompat
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.Question.QuestionActivity
import com.example.firequizz.R
import com.example.firequizz.leaderboard.LeaderboardActivity
import com.example.firequizz.ui.feature.profile.ProfileActivity
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : ComponentActivity() {
    lateinit var quizzModelListState: SnapshotStateList<QuizzModel>
    lateinit var isLoading: MutableState<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContent {
            isLoading = remember { mutableStateOf(true) }
            quizzModelListState = remember { mutableStateListOf<QuizzModel>() }
            getDataFromFirebase()

            MainScreen(
                onQuizzClick = { selectedQuizz ->
                    val intent = Intent(this, QuestionActivity::class.java)
                    intent.putExtra("quizz", selectedQuizz)
                    startActivity(intent)
                },
                onBoardClick = {
                    startActivity(Intent(this, LeaderboardActivity::class.java))
                },
                onProfileScreen = {
                    startActivity(Intent(this, ProfileActivity::class.java))
                },
                quizzModelListState,
                isLoading.value
            )
        }
    }

    private fun getDataFromFirebase(){
        isLoading.value = true
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if(dataSnapshot.exists()){
                    for(snapshot in dataSnapshot.children){
                        val quizzModel = snapshot.getValue(QuizzModel::class.java)
                        if (quizzModel != null) {
                            quizzModelListState.add(quizzModel)
                        } else {
                            Toast.makeText(this, "Unable to get data from Firebase", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        isLoading.value = false
    }
}