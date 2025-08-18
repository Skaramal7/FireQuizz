package com.example.firequizz.leaderboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.SortedList
import com.example.firequizz.R
import com.example.firequizz.ui.feature.leaderboard.LeaderboardScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

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
//        val db = Firebase.firestore
//        var userList: MutableList<UserModel> = mutableListOf()
//
//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    userList.add(
//                        UserModel(
//                            document.data["id"].toString(),
//                            document.data["name"].toString(),
//                            pic = "",
//                            score = document.data["score"].toString().toInt()
//                        )
//                    )
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "Error getting documents: ", exception)
//            }
//
//        return userList

        return listOf(
            UserModel(id = "1", name = "chloe", pic = "person1", score = 115),
            UserModel(id = "2", name = "testet", pic = "person2", score = 5),
            UserModel(id = "2", name = "gabriel", pic = "person3", score = 0),
            UserModel(id = "2", name = "testertester", pic = "person4", score = 50),
            UserModel(id = "2", name = "maria", pic = "person1", score = 0),
            UserModel(id = "2", name = "genste", pic = "person9", score = 15),
            UserModel(id = "2", name = "testUser", pic = "person6", score = 30),
            UserModel(id = "2", name = "testUsername", pic = "person7", score = 20)
        ).sortedByDescending { it.score }

    }
}