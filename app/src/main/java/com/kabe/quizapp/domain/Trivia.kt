package com.kabe.quizapp.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Trivia(
   @SerializedName("category") var category: String?,
   @SerializedName("type") var type: String?,
   @SerializedName("difficulty") var difficulty: String?,
   @SerializedName("question") var question: String,
   @SerializedName("correct_answer") var correctAnswer: String?,
   @SerializedName("incorrect_answers") var incorrectAnswers: List<String>
): Serializable

