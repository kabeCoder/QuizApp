package com.kabe.quizapp.ui.presentation.startscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme

@Composable
fun StartScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
fun Buttons(buttonName: String, onClick: () -> Unit) {
    Button(onClick = { onClick.invoke() }) {
        Text(text = buttonName)
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    QuizAppTheme {
        StartScreen()
    }
}