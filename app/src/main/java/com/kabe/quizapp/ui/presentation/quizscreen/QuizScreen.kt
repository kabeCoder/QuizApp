package com.kabe.quizapp.ui.presentation.quizscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.quizapp.ui.presentation.destinations.ResultScreenDestination
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.min

@Destination
@Composable
fun QuizScreen(
    navigator: DestinationsNavigator?,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val triviaList by viewModel.trivia.collectAsState(initial = emptyList())

    val currentTriviaIndex = remember {
        mutableStateOf(0)
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        if (triviaList.isNotEmpty())
            Column {
                Text(
                    text = triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].question
                )
                Button(onClick = {
                    currentTriviaIndex.value++
                    if (currentTriviaIndex.value == triviaList.size - 1)
                        navigator?.navigate(ResultScreenDestination)
                }) {
                    Text(text = "Next")
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizAppTheme {
        QuizScreen(null)
    }
}