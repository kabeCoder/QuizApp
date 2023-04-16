package com.kabe.quizapp.playscreen

import androidx.compose.runtime.MutableState


class SetupScreenDropdownState(
    val questionsTypeIsExpandedValue: MutableState<Boolean>,
    val difficultyIsExpandedValue: MutableState<Boolean>,
    val numberOfQuestionsIsExpandedValue: MutableState<Boolean>,
)

class SetupScreenValueState(
    val categorySelectedValue: MutableState<String>,
    val questionsTypeSelectedValue: MutableState<String>,
    val difficultySelectedValue: MutableState<String>,
    val numberOfQuestionsSelectedValue: MutableState<String>,
    val timeDurationSelectedValue: MutableState<String>,
)