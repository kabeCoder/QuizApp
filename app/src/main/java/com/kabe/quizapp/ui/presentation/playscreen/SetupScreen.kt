package com.kabe.quizapp.ui.presentation.playscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.presentation.destinations.QuizScreenDestination
import com.kabe.quizapp.ui.presentation.playscreen.views.DropDownMenu
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SetUpScreen(
    navigator: DestinationsNavigator?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (amount, category, difficulty, type, timer, confirmButton) = createRefs()

        val numberOfQuestions = remember { mutableStateOf("") }
        val categoryOfQuestions = remember { mutableStateOf("") }
        val difficultyOfQuestions = remember { mutableStateOf("") }
        val typeOfQuestions = remember { mutableStateOf("") }
        val initialSelected = remember { mutableStateOf("") }

        DropDownMenu(
            "Number of Questions",
            stringArrayResource(id = R.array.number_of_questions),
            modifier = Modifier.constrainAs(amount) {
                top.linkTo(parent.top, margin = 16.dp)

            }) {
            numberOfQuestions.value = it
        }

        DropDownMenu(
            "Category",
            stringArrayResource(id = R.array.category),
            modifier = Modifier.constrainAs(category) {
                top.linkTo(amount.bottom, margin = 16.dp)
            }) {
            categoryOfQuestions.value = when (it) {
                "Any Category" -> ""
                "General Knowledge" -> "9"
                "Entertainment: Books" -> "10"
                "Entertainment: Film" -> "11"
                "Entertainment: Music" -> "12"
                "Entertainment: Musicals & Theatres" -> "13"
                "Entertainment: Television" -> "14"
                "Entertainment: Video Games" -> "15"
                "Entertainment: Board Games" -> "16"
                "Science & Nature" -> "17"
                "Science: Computers" -> "18"
                "Science: Mathematics" -> "19"
                "Mythology" -> "20"
                "Sports" -> "21"
                "Geography" -> "22"
                "History" -> "23"
                "Politics" -> "24"
                "Art" -> "25"
                "Celebrities" -> "26"
                "Animals" -> "27"
                "Vehicles" -> "28"
                "Entertainment: Comics" -> "29"
                "Science: Gadgets" -> "30"
                "Entertainment: Japanese Anime & Manga" -> "31"
                "Entertainment: Cartoon & Animation" -> "32"
                else -> null.toString()
            }
        }

        DropDownMenu(
            "Difficulty",
            stringArrayResource(id = R.array.difficulty),
            modifier = Modifier.constrainAs(difficulty) {
                top.linkTo(category.bottom, margin = 16.dp)
            }) {
            difficultyOfQuestions.value = when (it) {
                "Any Difficulty" -> ""
                "Easy" -> "easy"
                "Medium" -> "medium"
                "Hard" -> "hard"
                else -> null.toString()
            }
        }

        DropDownMenu(
            "Type",
            stringArrayResource(id = R.array.type), modifier = Modifier.constrainAs(type) {
                top.linkTo(difficulty.bottom, margin = 16.dp)
            }) {
            typeOfQuestions.value = when (it) {
                "Any Type" -> ""
                "Multiple Choice" -> "multiple"
                "True / False" -> "boolean"
                else -> null.toString()
            }
        }

        OutlinedTextField(
            value = initialSelected.value,
            onValueChange = { initialSelected.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .constrainAs(timer) {
                    top.linkTo(type.bottom, margin = 16.dp)
                },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = stringResource(id = R.string.label_timer_in_seconds)) },
        )

        ConfirmButton(label = "Confirm", modifier = Modifier.constrainAs(confirmButton) {
            top.linkTo(timer.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        }) {
            navigator?.navigate(
                QuizScreenDestination(
                    numberOfQuestions.value.toInt(),
                    categoryOfQuestions.value.toInt(),
                    difficultyOfQuestions.value,
                    typeOfQuestions.value,
                    initialSelected.value.toInt()
                )
            )
        }
    }
}

@Composable
fun ConfirmButton(label: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick.invoke() }
    ) {
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        SetUpScreen(null)
    }
}