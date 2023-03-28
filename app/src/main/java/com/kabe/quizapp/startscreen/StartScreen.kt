package com.kabe.quizapp.startscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.destinations.SetUpScreenDestination
import com.kabe.quizapp.ui.theme.Blue
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.views.CommonButtonText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun StartScreen(navigator: DestinationsNavigator?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (playButton, exitButton) = createRefs()

        Buttons(
            buttonName = stringResource(id = R.string.label_play),
            modifier = Modifier.constrainAs(playButton) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            }) {
            navigator?.navigate(SetUpScreenDestination)
        }

        Buttons(
            buttonName = stringResource(id = R.string.label_exit),
            modifier = Modifier.constrainAs(exitButton) {
                top.linkTo(playButton.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            }) { }

        CommonButtonText(
            buttonName = stringResource(id = R.string.label_play),
            textStyle = MaterialTheme.typography.h4.copy(
                fontSize = 14.sp,
                color = White,
                fontWeight = FontWeight.W600
            ),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue)
        ) {

        }
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