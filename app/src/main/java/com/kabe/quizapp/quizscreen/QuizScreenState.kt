package com.kabe.quizapp.quizscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

class QuizScreenState(
    val currentTriviaIndex: MutableState<Int>,
    val currentScore: MutableState<Int>,
    val currentSelectedAnswer: MutableState<String>,
    val currentCorrectAnswer: MutableState<String>,
    val showCorrectAndIncorrectAnswerIcon: MutableState<Boolean>,
    val shuffledChoices: MutableList<String>,
    val answeredQuestions: MutableList<Boolean>
)
