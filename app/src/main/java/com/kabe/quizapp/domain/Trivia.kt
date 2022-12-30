package com.kabe.quizapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "trivia")
data class Trivia(
   @PrimaryKey(autoGenerate = true)
   var id: Int,
   @SerializedName("category") var category: String = "",
   @SerializedName("type") var type: String?,
   @SerializedName("difficulty") var difficulty: String?,
   @SerializedName("question") var question: String,
   @SerializedName("correct_answer") var correctAnswer: String?,
   @SerializedName("incorrect_answers") var incorrectAnswers: List<String>
): Serializable

