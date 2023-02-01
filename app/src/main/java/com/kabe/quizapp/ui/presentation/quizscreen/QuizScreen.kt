package com.kabe.quizapp.ui.presentation.quizscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.quizapp.ui.presentation.destinations.ResultScreenDestination
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.min
import kotlin.random.Random

@Destination
@Composable
fun QuizScreen(
    amount: Int,
    category: Int,
    difficulty: String,
    type: String,
    navigator: DestinationsNavigator?,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val triviaList by viewModel.trivia.collectAsState(initial = emptyList())

    viewModel.getTrivia(amount, category, difficulty, type)

    val currentTriviaIndex = remember {
        mutableStateOf(0)
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        if (triviaList.isNotEmpty())
            Column {
                val questions =
                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].question
                val correctAnswer =
                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].correctAnswer
                val incorrectAnswer =
                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].incorrectAnswers
                val choices = incorrectAnswer.plus(correctAnswer)
                Text(text = "Question: ")

                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = questions,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Text(text = "Correct Answer: ")
                Text(text = correctAnswer.toString())
                Text(text = "Wrong Answer: ")
                Text(text = incorrectAnswer.toString())
                Text(text = "Choices: ")
                Text(text = choices.toString())

                choices.shuffled().chunked(2).forEach { chunk ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        chunk.forEach { choice ->
                            Card(modifier = Modifier
                                .padding(16.dp)
                                .weight(1f)
                                .clickable {
                                    if (choice.toString() == correctAnswer)
                                        Log.d("QuizScreen", "Tama")
                                    else
                                        Log.d("QuizScreen", "Mali")

                                    currentTriviaIndex.value++
                                    if (currentTriviaIndex.value == triviaList.size)
                                        navigator?.navigate(ResultScreenDestination)
                                }) {
                                Text(
                                    text = choice.toString(), modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizAppTheme {
        QuizScreen(0, 0, "", "", null)
    }
}