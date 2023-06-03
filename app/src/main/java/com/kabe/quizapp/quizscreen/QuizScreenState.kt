package com.kabe.quizapp.quizscreen

import androidx.compose.runtime.MutableState

class QuizScreenState(
    val currentTriviaIndex: MutableState<Int>,
    val currentScore: MutableState<Int>,
    val currentSelectedAnswer: MutableState<String>,
    val currentCorrectAnswer: MutableState<String>,
    val showCorrectAndIncorrectAnswerIcon: MutableState<Boolean>
)
