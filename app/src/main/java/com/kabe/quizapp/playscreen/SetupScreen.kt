package com.kabe.quizapp.playscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.destinations.CategoryScreenDestination
import com.kabe.quizapp.destinations.QuizScreenDestination
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.DarkBlue1
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonButton
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            btnBack,
            textHeader,
            setupQuizCard
        ) = createRefs()

        val context = LocalContext.current
        val setupScreenDropdownState = rememberSetupScreenDropdownState()
        val setupScreenValueState = rememberSetupScreenValueState()
        val setupScreenScrollState = rememberScrollState()
        val modifiedCategoryName = categoryName.substringAfter(AppConstants.CATEGORY_NAME_DELIMITER)
        val categorySelectedValue = getCategorySelectedValue(categoryName)
        val questionTypeItems = stringArrayResource(id = R.array.question_type)
        val convertedQuestionTypeItems = stringArrayResource(id = R.array.converted_question_type)
        val questionDifficultyItems = stringArrayResource(id = R.array.question_difficulty)
        val convertedQuestionDifficultyItems =
            stringArrayResource(id = R.array.converted_question_difficulty)
        val questionNumberOfItems = stringArrayResource(id = R.array.number_of_questions)
        val timePerQuestionItems = stringArrayResource(id = R.array.timer_duration)

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
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(setupScreenScrollState),
            ) {
                ConstraintLayout {
                    val (
                        quizCategory,
                        quizQuestionType,
                        quizDifficulty,
                        quizItems,
                        quizDuration,
                        btnContinue

                    ) = createRefs()

                    LaunchedEffect(categorySelectedValue) {
                        setupScreenValueState.categorySelectedValue.value = categorySelectedValue
                    }

                    CommonTextFieldWithLabel(
                        modifier = Modifier
                            .constrainAs(quizCategory) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.large,
                                end = MaterialTheme.spacing.medium
                            ),
                        textFieldContent = modifiedCategoryName,
                        textFieldLabel = stringResource(id = R.string.label_quiz_category)
                    )

                    CommonClickableTextFieldWithLabel(
                        modifier = Modifier
                            .constrainAs(quizQuestionType) {
                                start.linkTo(parent.start)
                                top.linkTo(quizCategory.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.medium
                                        + MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.medium
                            ),
                        textFieldContent = stringResource(id = R.string.label_pick_question_type),
                        textFieldLabel = stringResource(id = R.string.label_question_type),
                        dropdownList = stringArrayResource(id = R.array.question_type),
                        showDropdown = setupScreenDropdownState.questionsTypeIsExpandedValue.value,
                        onSelectedItem = { questionType ->
                            setupScreenValueState.questionsTypeSelectedValue.value =
                                when (questionType) {
                                    questionTypeItems[0] -> context.getString(R.string.label_any)
                                    questionTypeItems[1] -> convertedQuestionTypeItems[0]
                                    questionTypeItems[2] -> convertedQuestionTypeItems[1]
                                    else -> null.toString()
                                }
                        }
                    ) {
                        setupScreenDropdownState.questionsTypeIsExpandedValue.value =
                            !setupScreenDropdownState.questionsTypeIsExpandedValue.value
                    }

                    CommonClickableTextFieldWithLabel(
                        modifier = Modifier
                            .constrainAs(quizDifficulty) {
                                start.linkTo(parent.start)
                                top.linkTo(quizQuestionType.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.medium
                                        + MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.medium
                            ),
                        textFieldContent = stringResource(id = R.string.label_pick_difficulty),
                        textFieldLabel = stringResource(id = R.string.label_difficulty),
                        dropdownList = stringArrayResource(id = R.array.question_difficulty),
                        showDropdown = setupScreenDropdownState.difficultyIsExpandedValue.value,
                        onSelectedItem = { difficultyType ->
                            setupScreenValueState.difficultySelectedValue.value =
                                when (difficultyType) {
                                    questionDifficultyItems[0] -> context.getString(R.string.label_any)
                                    questionDifficultyItems[1] -> convertedQuestionDifficultyItems[0]
                                    questionDifficultyItems[2] -> convertedQuestionDifficultyItems[1]
                                    questionDifficultyItems[3] -> convertedQuestionDifficultyItems[2]
                                    else -> null.toString()
                                }
                        }
                    ) {
                        setupScreenDropdownState.difficultyIsExpandedValue.value =
                            !setupScreenDropdownState.difficultyIsExpandedValue.value
                    }

                    CommonClickableTextFieldWithLabel(
                        modifier = Modifier
                            .constrainAs(quizItems) {
                                start.linkTo(parent.start)
                                top.linkTo(quizDifficulty.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.medium
                                        + MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.medium
                            ),
                        textFieldContent = stringResource(id = R.string.label_pick_number_of_questions),
                        textFieldLabel = stringResource(id = R.string.label_number_of_questions),
                        dropdownList = stringArrayResource(id = R.array.number_of_questions),
                        showDropdown = setupScreenDropdownState.numberOfQuestionsIsExpandedValue.value,
                        onSelectedItem = { questionItems ->
                            setupScreenValueState.numberOfQuestionsSelectedValue.value =
                                questionItems

                            setupScreenValueState.timeDurationSelectedValue.value =
                                when (questionItems) {
                                    questionNumberOfItems[0] -> timePerQuestionItems[0]
                                    questionNumberOfItems[1] -> timePerQuestionItems[1]
                                    questionNumberOfItems[2] -> timePerQuestionItems[2]
                                    questionNumberOfItems[3] -> timePerQuestionItems[3]
                                    questionNumberOfItems[4] -> timePerQuestionItems[4]
                                    questionNumberOfItems[5] -> timePerQuestionItems[5]
                                    questionNumberOfItems[6] -> timePerQuestionItems[6]
                                    questionNumberOfItems[7] -> timePerQuestionItems[7]
                                    questionNumberOfItems[8] -> timePerQuestionItems[8]
                                    questionNumberOfItems[9] -> timePerQuestionItems[9]
                                    else -> null.toString()
                                }
                        }
                    ) {
                        setupScreenDropdownState.numberOfQuestionsIsExpandedValue.value =
                            !setupScreenDropdownState.numberOfQuestionsIsExpandedValue.value
                    }

                    CommonTextFieldWithLabel(
                        modifier = Modifier
                            .constrainAs(quizDuration) {
                                start.linkTo(parent.start)
                                top.linkTo(quizItems.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.medium
                                        + MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.medium
                            ),
                        textFieldContent = setupScreenValueState.timeDurationSelectedValue.value.ifEmpty {
                            stringResource(
                                id = R.string.label_set_quiz_item
                            )
                        },
                        textFieldLabel = stringResource(id = R.string.label_quiz_duration)
                    )

                    CommonButton(
                        modifier = Modifier
                            .constrainAs(btnContinue) {
                                start.linkTo(parent.start)
                                top.linkTo(quizDuration.bottom)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                            .padding(
                                start = MaterialTheme.spacing.medium,
                                top = MaterialTheme.spacing.extraLarge,
                                end = MaterialTheme.spacing.medium,
                                bottom = MaterialTheme.spacing.small
                            ),
                        buttonName = stringResource(id = R.string.label_continue),
                        textStyle = MaterialTheme.typography.h5.copy(
                            fontSize = 14.sp,
                            color = White,
                            fontWeight = FontWeight.W600
                        ),
                        buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
                        backgroundOffset = 2.dp
                    ) {

                        navigator?.navigate(
                            QuizScreenDestination(
                                amount = setupScreenValueState.numberOfQuestionsSelectedValue.value.toInt(),
                                category = setupScreenValueState.categorySelectedValue.value.toInt(),
                                difficulty = setupScreenValueState.difficultySelectedValue.value,
                                type = setupScreenValueState.questionsTypeSelectedValue.value,
                                timer = setupScreenValueState.timeDurationSelectedValue.value
                            )
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun rememberSetupScreenDropdownState(
    questionsTypeIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    difficultyIsExpandedValue: MutableState<Boolean> = mutableStateOf(false),
    numberOfQuestionsIsExpandedValue: MutableState<Boolean> = mutableStateOf(false)
) = remember(
    questionsTypeIsExpandedValue,
    difficultyIsExpandedValue,
    numberOfQuestionsIsExpandedValue

) {
    SetupScreenDropdownState(
        questionsTypeIsExpandedValue,
        difficultyIsExpandedValue,
        numberOfQuestionsIsExpandedValue
    )
}

@Composable
fun rememberSetupScreenValueState(
    categorySelectedValue: MutableState<String> = mutableStateOf(""),
    questionsTypeSelectedValue: MutableState<String> = mutableStateOf(""),
    difficultySelectedValue: MutableState<String> = mutableStateOf(""),
    numberOfQuestionsSelectedValue: MutableState<String> = mutableStateOf(""),
    timeDurationSelectedValue: MutableState<String> = mutableStateOf(""),
) = remember(
    categorySelectedValue,
    questionsTypeSelectedValue,
    difficultySelectedValue,
    numberOfQuestionsSelectedValue,
    timeDurationSelectedValue

) {
    SetupScreenValueState(
        categorySelectedValue,
        questionsTypeSelectedValue,
        difficultySelectedValue,
        numberOfQuestionsSelectedValue,
        timeDurationSelectedValue
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