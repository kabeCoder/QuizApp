package com.kabe.quizapp.quizscreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    modifier: Modifier = Modifier,
    timeInSeconds: Int,
    isQuestionsLoaded: Boolean,
    onFinish: () -> Unit
) {
    val timeLeft = remember { mutableStateOf(timeInSeconds * 1000L) }

    if (isQuestionsLoaded) {
        LaunchedEffect(timeLeft) {
            while (timeLeft.value > 0) {
                delay(1000)
                timeLeft.value -= 1000
            }
            onFinish()
        }
    }

    val timeInMinutes = (timeLeft.value / 1000 / 60).toString().padStart(2, '0')
    val timeInSeconds = (timeLeft.value / 1000 % 60).toString().padStart(2, '0')

    Box(
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = White,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_timer),
                contentDescription = stringResource(id = R.string.label_icon_button_description),
                modifier = Modifier
                    .size(16.dp)
                    .padding(
                        end = MaterialTheme.spacing.extraSmall
                    )
            )
            Text(
                text = "$timeInMinutes:$timeInSeconds",
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraSmall),
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 12.sp,
                    color = White,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountdownTimerPreview() {
    CountdownTimer(
        timeInSeconds = 120,
        isQuestionsLoaded = false
    ) {

    }
}