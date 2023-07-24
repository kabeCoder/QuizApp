package com.kabe.quizapp.resultscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.quizapp.R
import com.kabe.quizapp.destinations.CategoryScreenDestination
import com.kabe.quizapp.resultscreen.views.ScoreCard
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.DarkBlue1
import com.kabe.quizapp.ui.theme.Green1
import com.kabe.quizapp.ui.theme.Orange
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.Red1
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonButtonIcon
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ResultScreen(
    viewModel: ResultScreenViewModel = hiltViewModel(),
    questionItems: Int,
    correctItems: Int,
    incorrectItems: Int
) {
    ResultScreenView(
        questionItems = questionItems,
        correctItems = correctItems,
        incorrectItems = incorrectItems
    )
}

@Composable
fun ResultScreenView(
    questionItems: Int,
    correctItems: Int,
    incorrectItems: Int
) {
    ConstraintLayout(
        modifier = Modifier
            .background(Blue1)
            .fillMaxSize()
    ) {

        val (
            btnBack,
            resultQuizCard
        ) = createRefs()

        CommonButtonIcon(
            modifier = Modifier
                .constrainAs(btnBack) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.medium,
                ),
            buttonIcon = painterResource(id = R.drawable.ic_back),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = DarkBlue1),
            onClick = {
//                navigator?.popBackStack(
//                    route = CategoryScreenDestination,
//                    inclusive = false
//                )
            }
        )

        CommonScreenCard(
            modifier = Modifier
                .constrainAs(resultQuizCard) {
                    start.linkTo(parent.start)
                    top.linkTo(btnBack.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large + MaterialTheme.spacing.extraLarge,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.extraLarge
                            + MaterialTheme.spacing.extraLarge
                            + MaterialTheme.spacing.extraLarge
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ScoreCard(number = questionItems, label = stringResource(id = R.string.label_question), labelColor = White, cardBackground = Orange) {}
                ScoreCard(number = correctItems, label = stringResource(id = R.string.label_correct), labelColor = White, cardBackground = Green1) {}
                ScoreCard(number = incorrectItems, label = stringResource(id = R.string.label_incorrect), labelColor = White, cardBackground = Red1) {}
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ResultScreenPreview() {
//    QuizAppTheme {
//        ResultScreen()
//    }
//}