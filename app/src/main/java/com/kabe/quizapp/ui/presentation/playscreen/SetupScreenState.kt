package com.kabe.quizapp.ui.presentation.playscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size


class SetupScreenState(
    val questionsIsExpandedValue: MutableState<Boolean>,
    val questionsInitialSelectedValue: MutableState<String>,
    val questionsTextFieldSizeValue: MutableState<Size>,
    val categoryIsExpandedValue: MutableState<Boolean>,
    val categoryInitialSelectedValue: MutableState<String>,
    val categoryTextFieldSizeValue: MutableState<Size>,
    val difficultyIsExpandedValue: MutableState<Boolean>,
    val difficultyInitialSelectedValue: MutableState<String>,
    val difficultyTextFieldSizeValue: MutableState<Size>,
    val typeIsExpandedValue: MutableState<Boolean>,
    val typeInitialSelectedValue: MutableState<String>,
    val typeTextFieldSizeValue: MutableState<Size>,
    val numberOfQuestions: MutableState<String>,
    val categoryOfQuestions: MutableState<String>,
    val difficultyOfQuestions: MutableState<String>,
    val typeOfQuestions: MutableState<String>,
    val initialSelected: MutableState<String>

)