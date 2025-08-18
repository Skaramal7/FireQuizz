package com.example.firequizz.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.firequizz.leaderboard.UserModel
import com.example.firequizz.R

@Composable
fun LeaderScreen(
    topUsers: List<UserModel>,
    otherUsers: List<UserModel>,
    onBackClick: () -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey)),
        verticalArrangement = Arrangement.Top
    ){
        item {  }
    }
}

@Preview
@Composable
fun LeaderScreenPreview() {
    val topUsers = listOf(
        UserModel(id = 1, name = "John Doe", pic = "person1", score = 100),
        UserModel(id = 2, name = "Jane Smith", pic = "person2", score = 90)
    )
    val otherUsers = listOf(
        UserModel(id = 3, name = "Peter Jones", pic = "person3", score = 80),
        UserModel(id = 4, name = "Alice Brown", pic = "person4", score = 70)
    )
    LeaderScreen(
        topUsers = topUsers,
        otherUsers = otherUsers,
        onBackClick = {})
}