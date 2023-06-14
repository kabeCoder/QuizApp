package com.kabe.quizapp.quizscreen

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.quizapp.R
import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.destinations.ResultScreenDestination
import com.kabe.quizapp.quizscreen.views.CountdownTimer
import com.kabe.quizapp.quizscreen.views.QuestionItemBoxCount
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.kabe.quizapp.ui.views.CommonTextCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    val quizScreenState = rememberQuizScreenState()

    QuizScreenView(
        amount = amount,
        category = category,
        difficulty = difficulty,
        type = type,
        timer = timer,
        navigator = navigator,
        quizScreenState = quizScreenState
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
    quizScreenState: QuizScreenState,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val modifiedTimer = timer.removeSuffix(AppConstants.TIMER_SUFFIX).toIntOrNull() ?: 0

    val triviaList = viewModel.trivia.collectAsState(initial = emptyList()).value

    val responseCode = viewModel.responseCode.collectAsState(initial = "").value

    LaunchedEffect(Unit) {
        viewModel.getTrivia(amount, category, difficulty, type)
        viewModel.getResponseCode(amount, category, difficulty, type)
    }

    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            questionsItem,
            quizTimer,
            questionBoxCount,
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
            timeInSeconds = modifiedTimer * 60,
            isQuestionsLoaded = triviaList.isNotEmpty()
        ) {

        }

        Text(
            text = stringResource(
                id = R.string.label_question_number,
                quizScreenState.currentTriviaIndex.value + 1
            ),
            modifier = Modifier
                .constrainAs(questionsItem) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
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

        QuestionItemBoxCount(
            modifier = Modifier
                .constrainAs(questionBoxCount) {
                    start.linkTo(parent.start)
                    top.linkTo(questionsItem.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    start = MaterialTheme.spacing.small,
                    top = MaterialTheme.spacing.small
                ),
            numberOfItems = triviaList.size,
            currentQuestionIndex = quizScreenState.currentTriviaIndex.value,
            answeredQuestions = quizScreenState.answeredQuestions
        )

        CommonScreenCard(
            modifier = Modifier
                .constrainAs(quizCard) {
                    start.linkTo(parent.start)
                    top.linkTo(questionBoxCount.bottom)
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

            if (responseCode.toString() == AppConstants.RESPONSE_CODE) {
                ConstraintLayout {

                    val (txtQuestion, txtChoices) = createRefs()

                    val questions =
                        triviaList[min(
                            quizScreenState.currentTriviaIndex.value,
                            triviaList.size - 1
                        )].question
                    val correctAnswer =
                        triviaList[min(
                            quizScreenState.currentTriviaIndex.value,
                            triviaList.size - 1
                        )].correctAnswer
                    val incorrectAnswer =
                        triviaList[min(
                            quizScreenState.currentTriviaIndex.value,
                            triviaList.size - 1
                        )].incorrectAnswers
                    val choices = incorrectAnswer.plus(correctAnswer)

                    Text(
                        text = Html.fromHtml(questions, Html.FROM_HTML_MODE_LEGACY).toString(),
                        modifier = Modifier
                            .constrainAs(txtQuestion) {
                                top.linkTo(parent.top)
                                centerHorizontallyTo(parent)
                            }
                            .padding(
                                start = MaterialTheme.spacing.large,
                                top = MaterialTheme.spacing.large,
                                end = MaterialTheme.spacing.large
                            )
                    )

                    Column(
                        modifier = Modifier
                            .constrainAs(txtChoices) {
                                top.linkTo(txtQuestion.bottom)
                                centerHorizontallyTo(parent)
                            }
                            .padding(top = MaterialTheme.spacing.extraLarge)
                    ) {

                        val shouldShuffle =
                            quizScreenState.currentTriviaIndex.value < triviaList.size - 1

                        LaunchedEffect(quizScreenState.currentTriviaIndex.value) {
                            quizScreenState.shuffledChoices.clear()
                            quizScreenState.shuffledChoices.addAll(choices.filterNotNull())
                            if (shouldShuffle) {
                                quizScreenState.shuffledChoices.shuffle()
                            }
                        }

                        quizScreenState.shuffledChoices.forEach { choice ->
                            CommonTextCard(
                                modifier = Modifier
                                    .padding(
                                        start = MaterialTheme.spacing.large,
                                        end = MaterialTheme.spacing.large,
                                        bottom = MaterialTheme.spacing.medium + MaterialTheme.spacing.small
                                    ),
                                textFieldContent = choice,
                                isIconVisible = quizScreenState.showCorrectAndIncorrectAnswerIcon.value,
                                selectedAnswer = quizScreenState.currentSelectedAnswer.value,
                                correctAnswer = quizScreenState.currentCorrectAnswer.value
                            ) {
                                quizScreenState.currentSelectedAnswer.value = choice
                                quizScreenState.currentCorrectAnswer.value =
                                    correctAnswer.toString()
                                quizScreenState.showCorrectAndIncorrectAnswerIcon.value = true

                                quizScreenState.answeredQuestions.add(quizScreenState.currentSelectedAnswer.value == quizScreenState.currentCorrectAnswer.value)

                                scope.launch {
                                    delay(500L)
                                    quizScreenState.currentTriviaIndex.value++
                                    if (quizScreenState.currentTriviaIndex.value >= triviaList.size) {
                                        navigator?.navigate(ResultScreenDestination)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun rememberQuizScreenState(
    currentTriviaIndex: MutableState<Int> = mutableStateOf(0),
    currentScore: MutableState<Int> = mutableStateOf(0),
    currentAnswerSelected: MutableState<String> = mutableStateOf(""),
    currentCorrectAnswer: MutableState<String> = mutableStateOf(""),
    showCorrectAndIncorrectAnswerIcon: MutableState<Boolean> = mutableStateOf(false),
    shuffledChoices: MutableList<String> = mutableStateListOf(),
    answeredQuestions: MutableList<Boolean> = mutableStateListOf()

) = remember(
    currentTriviaIndex,
    currentScore,
    currentAnswerSelected,
    currentCorrectAnswer,
    showCorrectAndIncorrectAnswerIcon,
    shuffledChoices,
    answeredQuestions

) {
    QuizScreenState(
        currentTriviaIndex,
        currentScore,
        currentAnswerSelected,
        currentCorrectAnswer,
        showCorrectAndIncorrectAnswerIcon,
        shuffledChoices,
        answeredQuestions
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizAppTheme {
        QuizScreen(0, 0, "", "", "", null)
    }
}