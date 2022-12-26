package com.kabe.quizapp.ui.presentation.startscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.presentation.destinations.PlayScreenDestination
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun StartScreen(navigator: DestinationsNavigator?) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (playButton, exitButton) = createRefs()

        Buttons(buttonName = "Play", modifier = Modifier.constrainAs(playButton) {
            centerVerticallyTo(parent)
            centerHorizontallyTo(parent)
        }) {
            navigator?.navigate(PlayScreenDestination)
        }

        Buttons(buttonName = "Exit", modifier = Modifier.constrainAs(exitButton) {
            top.linkTo(playButton.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        }) { }
    }
}

@Composable
fun Buttons(buttonName: String, modifier: Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = { onClick.invoke() }) {
        Text(text = buttonName)
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    QuizAppTheme {
        StartScreen(null)
    }
}