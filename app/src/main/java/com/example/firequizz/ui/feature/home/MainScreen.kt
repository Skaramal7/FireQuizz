package com.example.firequizz.ui.feature.home

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.R
import com.example.firequizz.ui.feature.home.components.Banner
import com.example.firequizz.ui.feature.home.components.BottomNavigationBar
import com.example.firequizz.ui.feature.home.components.QuizzListHeader
import com.example.firequizz.ui.feature.home.components.QuizzRow
import com.example.firequizz.ui.feature.home.components.TopUserSection

@Composable
fun MainScreen(
    onQuizzClick: (quizz: QuizzModel) -> Unit,
    onBoardClick: () -> Unit = {},
    quizzList: List<QuizzModel>,
    isLoading: Boolean = true
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopUserSection(

            )

            Banner()
            Spacer(modifier = Modifier.height(32.dp))

            QuizzListHeader()
            if(isLoading) {
                Spacer(modifier = Modifier.height(170.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    color = colorResource(R.color.orange),
                    trackColor = Color.Gray,
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .background(color = colorResource(R.color.grey))
                        .fillMaxWidth()
                ) {
                    items(quizzList) { quizz ->
                        QuizzRow(
                            quizz,
                            onItemClick = {
                                onQuizzClick(quizz)
                            }
                        )
                    }
                }
            }
        }

        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onItemSelected = {itemId ->
                if(itemId == R.id.board){
                    onBoardClick()
                }
            }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val quizzList = listOf(
        QuizzModel(
            id = 1,
            title = "Sample Quizz 1",
            length = 10,
            questionList = emptyList()
        ),
        QuizzModel(
            id = 2,
            title = "Sample Quizz 2",
            length = 5,
            questionList = emptyList()
        )
    )
    MainScreen(onQuizzClick = {}, onBoardClick = {}, quizzList = quizzList)
}
