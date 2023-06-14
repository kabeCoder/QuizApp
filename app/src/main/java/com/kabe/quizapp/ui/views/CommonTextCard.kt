package com.kabe.quizapp.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Black
import com.kabe.quizapp.ui.theme.Gray4
import com.kabe.quizapp.ui.theme.Green7
import com.kabe.quizapp.ui.theme.Red1
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun CommonTextCard(
    modifier: Modifier = Modifier,
    textFieldContent: String,
    isIconVisible: Boolean,
    selectedAnswer: String?,
    correctAnswer: String,
    onClick: () -> Unit

) {
    Box(
        modifier = modifier
    ) {

        val textFieldValue = remember {
            mutableStateOf(textFieldContent)
        }

        BasicTextField(
            value = textFieldContent,
            onValueChange = {
                textFieldValue.value = it
            },
            modifier = Modifier
                .background(
                    color = if (textFieldContent == selectedAnswer) {
                        if (textFieldContent == correctAnswer) {
                            Green7 // Selected correct answer
                        } else {
                            Red1 // Selected incorrect answer
                        }
                    } else if (textFieldContent == correctAnswer) {
                        White
                    } else {
                        Gray4.copy(alpha = 0.2f) // Default background color
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (textFieldContent == selectedAnswer) {
                        if (textFieldContent == correctAnswer) {
                            Green7
                        } else {
                            Color.Transparent
                        }
                    } else if (textFieldContent == correctAnswer) {
                        Green7
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(MaterialTheme.spacing.small + MaterialTheme.spacing.extraSmall)
                .clickable {
                    onClick.invoke()
                }
                .fillMaxWidth(),
            enabled = false,
            textStyle = MaterialTheme.typography.h5.copy(
                fontSize = 14.sp,
                color = if (textFieldContent == selectedAnswer) {
                    if (textFieldContent == correctAnswer) {
                        White
                    } else {
                        White
                    }
                } else if (textFieldContent == correctAnswer) {
                    Green7
                } else {
                    Black.copy(alpha = 0.5f)
                },
                fontWeight = FontWeight.W600
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.medium
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (textFieldContent.isEmpty()) {
                        Text(
                            text = textFieldContent,
                        )
                    } else innerTextField()

                    if(isIconVisible) {
                        if (textFieldContent == selectedAnswer) {
                            if (textFieldContent == correctAnswer) {
                                Image(painter = painterResource(id = R.drawable.ic_check_mark_1), contentDescription = null )
                            } else {
                                Image(painter = painterResource(id = R.drawable.ic_x_mark_1), contentDescription = null )
                            }
                        } else if (textFieldContent == correctAnswer) {
                            Image(painter = painterResource(id = R.drawable.ic_check_mark_2), contentDescription = null )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTextCardPreview() {
    CommonTextCard(
        textFieldContent = stringResource(id = R.string.label_preview_textField_content),
        isIconVisible = false,
        selectedAnswer = stringResource(id = R.string.label_preview_textField_content),
        correctAnswer = stringResource(id = R.string.label_preview_textField_content)
    ) {}
}