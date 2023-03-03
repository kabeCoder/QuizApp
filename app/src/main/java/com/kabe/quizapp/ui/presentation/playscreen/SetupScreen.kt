package com.kabe.quizapp.ui.presentation.playscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.presentation.destinations.QuizScreenDestination
import com.kabe.quizapp.ui.presentation.playscreen.views.DropDownMenu
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SetUpScreen(
    navigator: DestinationsNavigator?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (amount, category, difficulty, type, timer, confirmButton) = createRefs()

        val setUpScreenState = rememberSetupScreenState()

        DropDownMenu(
            "Number of Questions",
            stringArrayResource(id = R.array.number_of_questions),
            modifier = Modifier.constrainAs(amount) {
                top.linkTo(parent.top, margin = 16.dp)

            },
            isExpanded = setUpScreenState.questionsIsExpandedValue,
            initialSelected = setUpScreenState.questionsInitialSelectedValue,
            textFieldSize = setUpScreenState.questionsTextFieldSizeValue
        ) {
            setUpScreenState.numberOfQuestions.value = it
        }

        DropDownMenu(
            "Category",
            stringArrayResource(id = R.array.category),
            modifier = Modifier.constrainAs(category) {
                top.linkTo(amount.bottom, margin = 16.dp)
            },
            isExpanded = setUpScreenState.categoryIsExpandedValue,
            initialSelected = setUpScreenState.categoryInitialSelectedValue,
            textFieldSize = setUpScreenState.categoryTextFieldSizeValue
        ) {
            setUpScreenState.categoryOfQuestions.value = when (it) {
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
            },
            isExpanded = setUpScreenState.difficultyIsExpandedValue,
            initialSelected = setUpScreenState.difficultyInitialSelectedValue,
            textFieldSize = setUpScreenState.difficultyTextFieldSizeValue
        ) {
            setUpScreenState.difficultyOfQuestions.value = when (it) {
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
            },
            isExpanded = setUpScreenState.typeIsExpandedValue,
            initialSelected = setUpScreenState.typeInitialSelectedValue,
            textFieldSize = setUpScreenState.typeTextFieldSizeValue
        ) {
            setUpScreenState.typeOfQuestions.value = when (it) {
                "Any Type" -> ""
                "Multiple Choice" -> "multiple"
                "True / False" -> "boolean"
                else -> null.toString()
            }
        }

        OutlinedTextField(
            value = setUpScreenState.initialSelected.value,
            onValueChange = { setUpScreenState.initialSelected.value = it },
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
                    setUpScreenState.numberOfQuestions.value.toInt(),
                    setUpScreenState.categoryOfQuestions.value.toInt(),
                    setUpScreenState.difficultyOfQuestions.value,
                    setUpScreenState.typeOfQuestions.value,
                    setUpScreenState.initialSelected.value.toInt()
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

@Composable
fun rememberSetupScreenState(
    questionsIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    questionsInitialSelectedValue: MutableState<String> = mutableStateOf(""),
    questionsTextFieldSizeValue: MutableState<Size> = mutableStateOf(Size.Zero),
    categoryIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    categoryInitialSelectedValue: MutableState<String> = mutableStateOf(""),
    categoryTextFieldSizeValue: MutableState<Size> = mutableStateOf(Size.Zero),
    difficultyIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    difficultyInitialSelectedValue: MutableState<String> = mutableStateOf(""),
    difficultyTextFieldSizeValue: MutableState<Size> = mutableStateOf(Size.Zero),
    typeIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    typeInitialSelectedValue: MutableState<String> = mutableStateOf(""),
    typeTextFieldSizeValue: MutableState<Size> = mutableStateOf(Size.Zero),
    numberOfQuestions: MutableState<String> = mutableStateOf(""),
    categoryOfQuestions: MutableState<String> = mutableStateOf(""),
    difficultyOfQuestions: MutableState<String> = mutableStateOf(""),
    typeOfQuestions: MutableState<String> = mutableStateOf(""),
    initialSelected: MutableState<String> = mutableStateOf("")
) = remember(
    questionsIsExpandedValue,
    questionsInitialSelectedValue,
    questionsTextFieldSizeValue,
    categoryIsExpandedValue,
    categoryInitialSelectedValue,
    categoryTextFieldSizeValue,
    difficultyIsExpandedValue,
    difficultyInitialSelectedValue,
    difficultyTextFieldSizeValue,
    typeIsExpandedValue,
    typeInitialSelectedValue,
    typeTextFieldSizeValue,
    numberOfQuestions,
    categoryOfQuestions,
    difficultyOfQuestions,
    typeOfQuestions,
    initialSelected
) {
    SetupScreenState(
        questionsIsExpandedValue,
        questionsInitialSelectedValue,
        questionsTextFieldSizeValue,
        categoryIsExpandedValue,
        categoryInitialSelectedValue,
        categoryTextFieldSizeValue,
        difficultyIsExpandedValue,
        difficultyInitialSelectedValue,
        difficultyTextFieldSizeValue,
        typeIsExpandedValue,
        typeInitialSelectedValue,
        typeTextFieldSizeValue,
        numberOfQuestions,
        categoryOfQuestions,
        difficultyOfQuestions,
        typeOfQuestions,
        initialSelected
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        SetUpScreen(null)
    }
}