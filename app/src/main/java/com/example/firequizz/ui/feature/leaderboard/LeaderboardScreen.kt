package com.example.firequizz.ui.feature.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firequizz.leaderboard.UserModel
import com.example.firequizz.R
import com.example.firequizz.ui.feature.leaderboard.components.LeaderRow
import com.example.firequizz.ui.feature.leaderboard.components.OnBackRow
import com.example.firequizz.ui.feature.leaderboard.components.TopTreeSection

@Composable
fun LeaderboardScreen(
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
        item {
            OnBackRow(onBackClick)
        }

        item {
            TopTreeSection(topUsers)
            Spacer(Modifier.height(16.dp))
        }

        itemsIndexed(otherUsers){index, user ->
            LeaderRow(user = user, rank = index + 4)
        }
    }
}

@Preview
@Composable
fun LeaderScreenPreview() {
    val topUsers = listOf(
        UserModel(id = 1, name = "John Doe", pic = "person1", score = 100),
        UserModel(id = 2, name = "Jane Smith", pic = "person2", score = 90),
        UserModel(id = 3, name = "Peter Jones", pic = "person3", score = 80)
    )
    val otherUsers = listOf(
        UserModel(id = 4, name = "Peter Jones", pic = "person3", score = 80),
        UserModel(id = 5, name = "Alice Brown", pic = "person4", score = 70)
    )
    LeaderboardScreen(
        topUsers = topUsers,
        otherUsers = otherUsers,
        onBackClick = {})
}