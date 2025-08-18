package com.example.firequizz.ui.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firequizz.Question.Model.QuestionModel
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.R

@Composable
fun QuizzRow(
    quizz: QuizzModel,
    onItemClick: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(70.dp)
            .background(color = colorResource(R.color.white), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(
            text = quizz.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Column (
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Row {
                Image(
                    painter = painterResource(R.drawable.garnet),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))

                Text(
                    text = quizz.score.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(6.dp))

            Text(
                text = "Questions: ${quizz.lenght}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun QuizzRowPreview() {
    val quizz = QuizzModel(
        id = 1,
        title = "Sample Quizz",
        lenght = 5,
        questionList = emptyList()
    )
    QuizzRow(quizz = quizz)
}

