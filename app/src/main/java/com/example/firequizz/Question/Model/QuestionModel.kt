package com.example.firequizz.Question.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class QuestionModel(
    val question: String? = "",
    val answer_1: String? = "",
    val answer_2: String? = "",
    val answer_3: String? = "",
    val answer_4: String? = "",
    val correctAnswer: String? = "",
    val score: Int = 0,
    val clickedAnswer: String? = null,

): Parcelable

@Parcelize
data class QuizzModel(
    val id: Int = 0,
    val title: String = "",
    val lenght: Int = 0,
    val questionList: List<QuestionModel> = emptyList()
) : Parcelable