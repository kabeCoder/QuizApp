package com.kabe.quizapp.resultscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.quizapp.R
import com.kabe.quizapp.destinations.CategoryScreenDestination
import com.kabe.quizapp.destinations.StartScreenDestination
import com.kabe.quizapp.resultscreen.views.ScoreCard
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.Cyan
import com.kabe.quizapp.ui.theme.DarkBlue1
import com.kabe.quizapp.ui.theme.Green1
import com.kabe.quizapp.ui.theme.Orange
import com.kabe.quizapp.ui.theme.Red1
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonButton
import com.kabe.quizapp.ui.views.CommonButtonIcon
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ResultScreen(
    viewModel: ResultScreenViewModel = hiltViewModel(),
    questionItems: Int,
    correctItems: Int,
    incorrectItems: Int,
    finalScore: Int,
    navigator: DestinationsNavigator?
) {
    ResultScreenView(
        questionItems = questionItems,
        correctItems = correctItems,
        incorrectItems = incorrectItems,
        navigator = navigator,
        finalScore = finalScore
    )
}

@Composable
fun ResultScreenView(
    questionItems: Int,
    correctItems: Int,
    incorrectItems: Int,
    finalScore: Int,
    navigator: DestinationsNavigator?
) {
    val resultFeedback = when ((correctItems.toFloat() / questionItems) * 100) {
        in 0.0..49.99 -> R.drawable.ic_nice_try
        in 50.0..79.99 -> R.drawable.ic_good_job
        else -> R.drawable.ic_great_job
    }

    val ratingImage = when ((correctItems.toFloat() / questionItems) * 100) {
        in 0.0..49.99 -> R.drawable.ic_one_star
        in 50.0..79.99 -> R.drawable.ic_two_star
        else -> R.drawable.ic_three_star
    }


    ConstraintLayout(
        modifier = Modifier
            .background(Blue1)
            .fillMaxSize()
    ) {

        val (
            btnBack,
            resultQuizCard,
            imgThumbsUp
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
                navigator?.popBackStack(
                    route = StartScreenDestination,
                    inclusive = false
                )
            }
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(resultFeedback)
                .crossfade(true)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imgThumbsUp) {
                    centerHorizontallyTo(parent)
                    top.linkTo(btnBack.bottom)
                    bottom.linkTo(resultQuizCard.top)
                }
                .zIndex(1f)
                .offset(y = MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.large + MaterialTheme.spacing.small)
                .width(175.dp)
                .height(140.dp)
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
                    bottom = MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.extraLarge

                )
                .zIndex(-1f)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ratingImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.large + MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    )
                    .width(200.dp)
                    .height(136.dp)
            )

            Text(
                text = finalScore.toString(),
                modifier = Modifier
                    .offset(y = -(60.dp)),
                textAlign = TextAlign.Center,
                softWrap = true,
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 32.sp,
                    color = White,
                    fontWeight = FontWeight.W900
                )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ScoreCard(
                    number = questionItems,
                    label = stringResource(id = R.string.label_question),
                    labelColor = White,
                    cardBackground = Orange
                ) {}
                ScoreCard(
                    number = correctItems,
                    label = stringResource(id = R.string.label_correct),
                    labelColor = White,
                    cardBackground = Green1
                ) {}
                ScoreCard(
                    number = incorrectItems,
                    label = stringResource(id = R.string.label_incorrect),
                    labelColor = White,
                    cardBackground = Red1
                ) {}
            }

            CommonButton(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.extraLarge,
                    end = MaterialTheme.spacing.medium,

                    bottom = MaterialTheme.spacing.medium
                ),
                buttonName = stringResource(id = R.string.label_play_again),
                textStyle = MaterialTheme.typography.h5.copy(
                    fontSize = 14.sp,
                    color = White,
                    fontWeight = FontWeight.W600
                ),
                buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
                backgroundOffset = MaterialTheme.spacing.extraSmall
            ) {
                navigator?.navigate(CategoryScreenDestination)
            }
        }
    }
}