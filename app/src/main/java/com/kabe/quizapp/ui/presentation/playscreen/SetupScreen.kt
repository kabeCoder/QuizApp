package com.kabe.quizapp.ui.presentation.playscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.presentation.destinations.QuizScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SetUpScreen(
    navigator: DestinationsNavigator?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (amount, category, difficulty, type, confirmButton) = createRefs()

        var numberOfQuestions = ""
        var categoryOfQuestions = ""
        var difficultyOfQuestions = ""
        var typeOfQuestions = ""

        DropDownMenu(
            "Number of Questions",
            stringArrayResource(id = R.array.number_of_questions),
            modifier = Modifier.constrainAs(amount) {
                top.linkTo(parent.top, margin = 16.dp)

            }) {
            numberOfQuestions = it
        }

        DropDownMenu(
            "Category",
            stringArrayResource(id = R.array.category),
            modifier = Modifier.constrainAs(category) {
                top.linkTo(amount.bottom, margin = 16.dp)
            }) {
            categoryOfQuestions = when (it) {
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
            difficultyOfQuestions = when (it) {
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
            typeOfQuestions = when (it) {
                "Any Type" -> ""
                "Multiple Choice" -> "multiple"
                "True / False" -> "boolean"
                else -> null.toString()
            }
        }

        ConfirmButton(label = "Confirm", modifier = Modifier.constrainAs(confirmButton) {
            top.linkTo(type.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        }) {
            navigator?.navigate(
                QuizScreenDestination(
                    numberOfQuestions.toInt(),
                    categoryOfQuestions.toInt(),
                    difficultyOfQuestions,
                    typeOfQuestions
                )
            )
        }
    }
}

@Composable
fun DropDownMenu(
    selectTitle: String,
    selectedList: Array<String>,
    modifier: Modifier,
    onValueSelected: (String) -> Unit
) {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    val isExpanded = remember { mutableStateOf(false) }

    // Create a string value to store the selected item
    val initialSelected = remember { mutableStateOf("") }

    val mTextFieldSize = remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (isExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = initialSelected.value,
            onValueChange = { initialSelected.value = it },
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize.value = coordinates.size.toSize()
                },
            label = { Text(selectTitle) },
            enabled = false,
            trailingIcon = {
                Icon(icon, "contentDescription",
                    modifier.clickable { isExpanded.value = !isExpanded.value })
            }
        )

        // Create a drop-down menu with list of cities,
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = modifier
                .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() }),
        ) {
            selectedList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        initialSelected.value = label
                        isExpanded.value = false
                        onValueSelected.invoke(initialSelected.value)
                    }) {
                        Text(text = label)
                    }
            }
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