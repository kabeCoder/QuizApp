package com.kabe.quizapp.quizscreen.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(timeInSeconds: Int, onFinish: () -> Unit) {
    val timeLeft = remember { mutableStateOf(timeInSeconds * 1000L) }

    LaunchedEffect(true) {
        while (timeLeft.value > 0) {
            delay(1000)
            timeLeft.value -= 1000
        }
        onFinish()
    }

    val timeInMinutes = (timeLeft.value / 1000 / 60).toString().padStart(2, '0')
    val timeInSeconds = (timeLeft.value / 1000 % 60).toString().padStart(2, '0')

    Text(text = "$timeInMinutes:$timeInSeconds")
}