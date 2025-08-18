package com.example.firequizz

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
import com.example.firequizz.Question.Model.QuestionModel
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.Question.QuestionActivity
import com.example.firequizz.leaderboard.LeaderboardActivity
import com.example.firequizz.ui.feature.home.MainScreen
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
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

    private fun questionList(): List<QuestionModel> {
        return listOf(
            QuestionModel(
                "Which planet is the largest planet in the solar system?",
                "Earth",
                "Mars",
                "Neptune",
                "Jupiter",
                "d",
                5,
                null
            ),
            QuestionModel(
                "Which country is the largest country in the world by land area?",
                "Russia",
                "Canada",
                "United States",
                "China",
                "a",
                5,
                null
            ),
            QuestionModel(
                "Which of the following substances is used as an anti-cancer medication?",
                "Cheese",
                "Lemon juice",
                "Cannabis",
                "Paspalum",
                "c",
                5,
                null
            ),
            QuestionModel(
                "Which moon has an atmosphere?",
                "Luna",
                "Phobos",
                "Venus' moon",
                "None of the above",
                "d",
                5,
                null
            ),
            QuestionModel(
                "Which symbol represents the element with atomic number 6?",
                "O",
                "H",
                "C",
                "N",
                "c",
                5,
                null
            ),
            QuestionModel(
                "Who is credited with inventing theater as we know it?",
                "Shakespeare",
                "Arthur Miller",
                "Ashkouri",
                "Ancient Greeks",
                "d",
                5,
                null
            ),
            QuestionModel(
                "Which ocean is the largest?",
                "Pacific",
                "Atlantic",
                "Indian",
                "Arctic",
                "a",
                5,
                null
            ),
            QuestionModel(
                "Which religions are most practiced?",
                "Islam, Christianity, Judaism",
                "Buddhism, Hinduism, Sikhism",
                "Zoroastrianism, Brahmanism",
                "Taoism, Shintoism",
                "a",
                5,
                null
            ),
            QuestionModel(
                "Which continent has the most independent countries?",
                "Asia",
                "Europe",
                "Africa",
                "Americas",
                "c",
                5,
                null
            ),
            QuestionModel(
                "Which ocean has the greatest average depth?",
                "Pacific",
                "Atlantic",
                "Indian",
                "Southern",
                "d",
                5,
                null
            )
        )
    }
}