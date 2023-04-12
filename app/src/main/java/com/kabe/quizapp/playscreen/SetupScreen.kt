package com.kabe.quizapp.playscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.destinations.CategoryScreenDestination
import com.kabe.quizapp.ui.theme.DarkBlue1
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonButtonIcon
import com.kabe.quizapp.ui.views.CommonClickableTextFieldWithLabel
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.kabe.quizapp.ui.views.CommonTextFieldWithLabel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SetupScreen(
    navigator: DestinationsNavigator?,
    categoryName: String
) {
    SetupScreenView(
        navigator = navigator,
        categoryName = categoryName
    )
}

@Composable
fun SetupScreenView(
    navigator: DestinationsNavigator?,
    categoryName: String
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (
            btnBack,
            textHeader,
            setupQuizCard
        ) = createRefs()

        val setupScreenState = rememberSetupScreenState()

        val modifiedCategoryName = categoryName.substringAfter(AppConstants.CATEGORY_NAME_DELIMITER)

        CommonBoxHeader()

        CommonButtonIcon(
            modifier = Modifier
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(btnBack.start)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                ),
            buttonIcon = painterResource(id = R.drawable.ic_back),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = DarkBlue1),
            onClick = {
                navigator?.popBackStack(
                    route = CategoryScreenDestination,
                    inclusive = false
                )
            }
        )

        Text(
            text = stringResource(id = R.string.label_setup_quiz),
            modifier = Modifier
                .constrainAs(textHeader) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                    bottom.linkTo(setupQuizCard.top)
                }
                .padding(
                    top = MaterialTheme.spacing.large
                            + MaterialTheme.spacing.extraSmall
                            + MaterialTheme.spacing.customSpacingTwo
                ),
            style = MaterialTheme.typography.h3.copy(
                fontSize = 20.sp,
                color = White,
                fontWeight = FontWeight.W700
            )
        )

        CommonScreenCard(
            modifier = Modifier
                .constrainAs(setupQuizCard) {
                    start.linkTo(parent.start)
                    top.linkTo(textHeader.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.extraLarge
                            + MaterialTheme.spacing.large
                )
        ) {
            CommonTextFieldWithLabel(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                textFieldContent = modifiedCategoryName,
                textFieldLabel = stringResource(id = R.string.label_quiz_category)
            )

            CommonClickableTextFieldWithLabel(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                textFieldContent = stringResource(id = R.string.label_pick_question_type),
                textFieldLabel = stringResource(id = R.string.label_question_type),
                dropdownList = stringArrayResource(id = R.array.type),
                showDropdown = setupScreenState.questionsTypeIsExpandedValue.value,
                onSelectedItem = {questionType ->
                    setupScreenState.questionsTypeInitialSelectedValue.value = questionType
                }
            ) {
                setupScreenState.questionsTypeIsExpandedValue.value = !setupScreenState.questionsTypeIsExpandedValue.value
            }

            CommonClickableTextFieldWithLabel(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                textFieldContent = stringResource(id = R.string.label_pick_difficulty),
                textFieldLabel = stringResource(id = R.string.label_difficulty),
                dropdownList = stringArrayResource(id = R.array.difficulty),
                showDropdown = setupScreenState.difficultyIsExpandedValue.value,
                onSelectedItem = {difficultyType ->
                    setupScreenState.difficultyInitialSelectedValue.value = difficultyType
                }
            ) {
                setupScreenState.difficultyIsExpandedValue.value = !setupScreenState.difficultyIsExpandedValue.value
            }
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
    questionsTypeIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    questionsTypeInitialSelectedValue: MutableState<String> = mutableStateOf(""),
    difficultyIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    difficultyInitialSelectedValue: MutableState<String> = mutableStateOf(""),
) = remember(
    questionsTypeIsExpandedValue,
    questionsTypeInitialSelectedValue,
    difficultyIsExpandedValue,
    difficultyInitialSelectedValue

) {
    SetupScreenState(
        questionsTypeIsExpandedValue,
        questionsTypeInitialSelectedValue,
        difficultyIsExpandedValue,
        difficultyInitialSelectedValue
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        SetupScreen(
            null,
            categoryName = stringResource(id = R.string.label_quiz_category_name)
        )
    }
}