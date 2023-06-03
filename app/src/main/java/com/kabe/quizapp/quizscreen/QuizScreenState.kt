package com.kabe.quizapp.quizscreen

import androidx.compose.runtime.MutableState

class QuizScreenState(
    val currentSelectedAnswer: MutableState<String>,
    val currentCorrectAnswer: MutableState<String>,
    val showCorrectAndIncorrectAnswerIcon: MutableState<Boolean>
)
