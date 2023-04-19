package com.kabe.quizapp.quizscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.quizscreen.views.CountdownTimer
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuizScreen(
    amount: Int,
    category: Int,
    difficulty: String,
    type: String,
    timer: String,
    navigator: DestinationsNavigator?
) {

    QuizScreenView(
        amount = amount,
        category = category,
        difficulty = difficulty,
        type = type,
        timer = timer,
        navigator = navigator
    )
}

@Composable
fun QuizScreenView(
    amount: Int,
    category: Int,
    difficulty: String,
    type: String,
    timer: String,
    navigator: DestinationsNavigator?,
    //viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val modifiedTimer = timer.removeSuffix(AppConstants.TIMER_SUFFIX).toIntOrNull() ?: 0

    //val triviaList by viewModel.trivia.collectAsState(initial = emptyList())

    //val responseCode by viewModel.responseCode.collectAsState(initial = "")

    //viewModel.getTrivia(amount, category, difficulty, type)

    //viewModel.getResponseCode(amount, category, difficulty, type)

    val currentTriviaIndex = remember {
        mutableStateOf(0)
    }

    val currentScore = remember {
        mutableStateOf(0)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            questionsItem,
            quizTimer,
            quizCard
        ) = createRefs()

        CommonBoxHeader()

        CountdownTimer(
            modifier = Modifier
                .constrainAs(quizTimer) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(quizCard.top)
                }
                .padding(
                    top = MaterialTheme.spacing.large - MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.medium
                ),
            timeInSeconds = modifiedTimer * 60
        ) {

        }

        Text(
            text = stringResource(id = R.string.label_question_number, "5"),
            modifier = Modifier
                .constrainAs(questionsItem) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(quizCard.top)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                            + MaterialTheme.spacing.extraSmall
                            + MaterialTheme.spacing.customSpacingTwo,
                ),
            style = MaterialTheme.typography.h3.copy(
                fontSize = 20.sp,
                color = White,
                fontWeight = FontWeight.W700
            )
        )

        CommonScreenCard(
            modifier = Modifier
                .constrainAs(quizCard) {
                    start.linkTo(parent.start)
                    top.linkTo(questionsItem.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.extraLarge
                            + MaterialTheme.spacing.large
                )
        ) {

        }
//        Text(text = responseCode.toString())
//        if (triviaList.isNotEmpty())
//            Column {
//                val questions =
//                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].question
//                val correctAnswer =
//                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].correctAnswer
//                val incorrectAnswer =
//                    triviaList[min(currentTriviaIndex.value, triviaList.size - 1)].incorrectAnswers
//                val choices = incorrectAnswer.plus(correctAnswer)
//                Text(text = "Question: ")
//                Text(text = "Score: ${currentScore.value}")
//                CountdownTimer(timeInSeconds = modifiedTimer * 60) {
//                    //navigator?.navigate(ResultScreenDestination)
//                }
//
//
//                Card(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text = Html.fromHtml(questions, Html.FROM_HTML_MODE_LEGACY).toString(),
//                        modifier = Modifier
//                            .padding(16.dp)
//                    )
//                }
//                Text(text = "Correct Answer: ")
//                Text(text = correctAnswer.toString())
//                Text(text = "Wrong Answer: ")
//                Text(
//                    text = Html.fromHtml(incorrectAnswer.toString(), Html.FROM_HTML_MODE_LEGACY)
//                        .toString()
//                )
//                Text(text = "Choices: ")
//                Text(
//                    text = Html.fromHtml(choices.toString(), Html.FROM_HTML_MODE_LEGACY).toString()
//                )
//
//                choices.shuffled().chunked(2).forEach { chunk ->
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        chunk.forEach { choice ->
//                            Card(modifier = Modifier
//                                .padding(16.dp)
//                                .weight(1f)
//                                .clickable {
//                                    if (choice.toString() == correctAnswer)
//                                        currentScore.value++
//                                    else
//                                        Log.d("QuizScreen", "Mali")
//
//                                    currentTriviaIndex.value++
//                                    if (currentTriviaIndex.value == triviaList.size)
//                                        navigator?.navigate(ResultScreenDestination)
//                                }) {
//                                Text(
//                                    text = choice.toString(), modifier = Modifier
//                                        .padding(16.dp)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizAppTheme {
        QuizScreen(0, 0, "", "", "", null)
    }
}