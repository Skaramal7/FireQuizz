package com.example.firequizz.Question.Model

data class QuestionUiState (
    val questions: List<QuestionModel>,
    val currentIndex: Int = 0,
    val score: Int = 0
)