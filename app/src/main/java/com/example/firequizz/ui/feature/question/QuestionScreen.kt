package com.example.firequizz.ui.feature.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.firequizz.Question.Model.QuestionModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firequizz.Question.Model.QuestionUiState
import com.example.firequizz.Question.Model.QuizzModel
import com.example.firequizz.R
import com.example.firequizz.ui.feature.question.components.AnswerItem

@Composable
fun QuestionScreen(
    quizz: QuizzModel?,
    onFinish: (finalScore: Int) -> Unit,
    onBackClick: () -> Unit,
) {
    if(quizz != null){
        var questions = quizz.questionList
        var state by remember { mutableStateOf(QuestionUiState(questions = questions)) }

        val currentQuestion = state.questions[state.currentIndex]
        var selectedAnswer = currentQuestion.clickedAnswer
        val context = LocalContext.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.grey))
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onBackClick }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = "Single Player",
                        fontSize = 20.sp,
                        color = colorResource(R.color.navy_blue),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Question ${state.currentIndex + 1}/10",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            if (state.currentIndex > 0) {
                                selectedAnswer = null
                                state = state.copy(currentIndex = state.currentIndex - 1)
                            }
                        }
                    ) {
                        Icon(painterResource(R.drawable.left_arrow), contentDescription = null)
                    }
                    IconButton(
                        onClick = {
                            if (state.currentIndex == (quizz.length - 1)) {
                                onFinish(state.score)
                            } else {
                                selectedAnswer = null
                                state = state.copy(currentIndex = state.currentIndex + 1)
                            }
                        }
                    ) {
                        Icon(painterResource(R.drawable.right_arrow), contentDescription = null)
                    }
                }
            }

            item {
                LinearProgressIndicator(
                    progress = (state.currentIndex + 1) / 10f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(14.dp)
                        .clip(RoundedCornerShape(50)),
                    color = colorResource(R.color.orange),
                    trackColor = Color(0xffd1d1d1)
                )
            }

            item {
                Text(
                    text = currentQuestion.question ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.navy_blue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            itemsIndexed(
                listOf(
                    currentQuestion.answer_1 ?: "",
                    currentQuestion.answer_2 ?: "",
                    currentQuestion.answer_3 ?: "",
                    currentQuestion.answer_4 ?: ""
                )
            ) { index, answerText ->
                val answerLetter = listOf("a", "b", "c", "d")[index]
                val isCorrect = selectedAnswer != null && answerLetter == currentQuestion.correctAnswer
                val isWrong = selectedAnswer == answerLetter && !isCorrect

                AnswerItem(
                    text = answerText,
                    isCorrect = isCorrect,
                    isWrong = isWrong,
                    isSelected = selectedAnswer != null
                ) {
                    val updatedQuestion = state.questions.toMutableList()
                    val updateQuestion =
                        updatedQuestion[state.currentIndex].copy(clickedAnswer = answerLetter)
                    updatedQuestion[state.currentIndex] = updateQuestion

                    val scoreToAdd = if (answerLetter == updateQuestion.correctAnswer) 5 else 0

                    state = state.copy(
                        questions = updatedQuestion,
                        score = state.score + scoreToAdd
                    )
                }
            }

            item {
                Spacer(Modifier.height(32.dp))
            }
        }
    } else{
        onBackClick
    }
}

@Preview
@Composable
fun QuestionScreenPreview() {
    val dquizz = QuizzModel(
        id = 1,
        title = "Sample Quizz",
        length = 10,
        questionList = listOf(
            QuestionModel(
                question = "What is the capital of France?",
                answer_1 = "Paris",
                answer_2 = "London",
                answer_3 = "Berlin",
                answer_4 = "Madrid",
                correctAnswer = "Paris",
                score = 5,
                clickedAnswer = null
            )
        )
    )

    QuestionScreen(quizz = dquizz, onFinish = {}, onBackClick = {})
}