package com.kabe.quizapp.playscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.Blue
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SetUpScreen(
    navigator: DestinationsNavigator?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            boxHeader,
            btnBack,
            amount,
            category,
            difficulty,
            type,
            confirmButton
        ) = createRefs()

        val setUpScreenState = rememberSetupScreenState()

        Box(
            modifier = Modifier
                .constrainAs(boxHeader) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(Blue)
                .height(200.dp)
                .fillMaxWidth()
        ) {

        }

//        DropDownMenu(
//            "Category",
//            stringArrayResource(id = R.array.category),
//            modifier = Modifier.constrainAs(category) {
//                top.linkTo(amount.bottom, margin = 16.dp)
//            },
//            isExpanded = setUpScreenState.categoryIsExpandedValue,
//            initialSelected = setUpScreenState.categoryInitialSelectedValue,
//            textFieldSize = setUpScreenState.categoryTextFieldSizeValue
//        ) {
//            setUpScreenState.categoryOfQuestions.value = when (it) {
//                "Any Category" -> ""
//                "General Knowledge" -> "9"
//                "Entertainment: Books" -> "10"
//                "Entertainment: Film" -> "11"
//                "Entertainment: Music" -> "12"
//                "Entertainment: Musicals & Theatres" -> "13"
//                "Entertainment: Television" -> "14"
//                "Entertainment: Video Games" -> "15"
//                "Entertainment: Board Games" -> "16"
//                "Science & Nature" -> "17"
//                "Science: Computers" -> "18"
//                "Science: Mathematics" -> "19"
//                "Mythology" -> "20"
//                "Sports" -> "21"
//                "Geography" -> "22"
//                "History" -> "23"
//                "Politics" -> "24"
//                "Art" -> "25"
//                "Celebrities" -> "26"
//                "Animals" -> "27"
//                "Vehicles" -> "28"
//                "Entertainment: Comics" -> "29"
//                "Science: Gadgets" -> "30"
//                "Entertainment: Japanese Anime & Manga" -> "31"
//                "Entertainment: Cartoon & Animation" -> "32"
//                else -> null.toString()
//            }
//        }
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
    typeOfQuestions: MutableState<String> = mutableStateOf("")
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
    typeOfQuestions
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
        typeOfQuestions
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        SetUpScreen(null)
    }
}