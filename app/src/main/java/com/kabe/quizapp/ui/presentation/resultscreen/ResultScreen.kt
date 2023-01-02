package com.kabe.quizapp.ui.presentation.resultscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ResultScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Text(text = "Welcome to Result Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    QuizAppTheme {
        ResultScreen()
    }
}