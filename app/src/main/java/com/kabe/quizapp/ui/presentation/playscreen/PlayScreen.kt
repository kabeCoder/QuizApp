package com.kabe.quizapp.ui.presentation.playscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PlayScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        PlayScreen()
    }
}