package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Black
import com.kabe.quizapp.ui.theme.Gray1
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun CommonTextFieldWithLabel(
    modifier: Modifier = Modifier,
    textFieldContent: String,
    textFieldLabel: String,
    textStyle: TextStyle = MaterialTheme.typography.h4.copy(
        fontSize = 16.sp,
        color = Black,
        fontWeight = FontWeight.W600
    )
) {
    Column(
        modifier = modifier
    ) {

        val textFieldValue = remember {
            mutableStateOf(textFieldContent)
        }

        Text(
            text = textFieldLabel,
            modifier = Modifier
                .padding(
                    bottom = MaterialTheme.spacing.small
                ),
            style = textStyle
        )

        BasicTextField(
            value = textFieldContent,
            onValueChange = {
                textFieldValue.value = it
            },
            modifier = Modifier
                .background(
                    color = Gray1.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(15.dp)
                )
                .border(
                    width = 1.dp,
                    color = Gray1,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(MaterialTheme.spacing.small + MaterialTheme.spacing.extraSmall)
                .fillMaxWidth(),
            enabled = false,
            textStyle = MaterialTheme.typography.h5.copy(
                fontSize = 14.sp,
                color = Black.copy(0.5f),
                fontWeight = FontWeight.W600
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTextFieldWithLabelPreview() {
    CommonTextFieldWithLabel(
        textFieldContent = stringResource(id = R.string.label_preview_textField_content),
        textFieldLabel = stringResource(id = R.string.label_preview_textField_label),
    )
}