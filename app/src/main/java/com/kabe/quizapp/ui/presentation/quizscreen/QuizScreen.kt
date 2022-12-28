package com.kabe.quizapp.ui.presentation.quizscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun QuizScreen() {
    val viewModel: QuizScreenViewModel = hiltViewModel()
    val triviaList by viewModel.trivia.collectAsState(initial = emptyList())
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
    Text(text = triviaList.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizAppTheme {
        QuizScreen()
    }
}