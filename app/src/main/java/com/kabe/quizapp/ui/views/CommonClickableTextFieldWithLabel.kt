package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Black
import com.kabe.quizapp.ui.theme.Gray1

@Composable
fun CommonClickableTextFieldWithLabel(
    modifier: Modifier = Modifier,
    textFieldContent: String,
    textFieldLabel: String,
    textStyle: TextStyle = MaterialTheme.typography.h4.copy(
        fontSize = 16.sp,
        color = Black,
        fontWeight = FontWeight.W600
    ),
    showDropdown: Boolean = false,
    dropdownList: Array<String>,
    onClick: (Boolean) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        val textFieldValue = remember {
            mutableStateOf(textFieldContent)
        }

        Text(
            text = textFieldLabel,
            style = textStyle
        )

        BasicTextField(
            value = textFieldValue.value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(
                    width = 1.dp,
                    color = Gray1,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(
                    color = Gray1.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(12.dp)
                .clickable {
                    onClick.invoke(showDropdown)
                },
            enabled = false,
            textStyle = MaterialTheme.typography.h5.copy(
                fontSize = 14.sp,
                color = Black.copy(0.5f),
                fontWeight = FontWeight.W600
            ),
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                    Icon(
                        painter = painterResource(id = R.drawable.ic_down),
                        contentDescription = stringResource(id = R.string.label_icon_button_description),
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                    )

                }
            }
        )
        if (showDropdown)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        clip = false,
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(15.dp)
                    )

            ) {
                Column {
                    dropdownList.forEach { list ->
                        Text(
                            text = list,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun CommonClickableTextFieldWithLabelPreview() {
    CommonClickableTextFieldWithLabel(
        textFieldContent = stringResource(id = R.string.label_preview_textField_content),
        textFieldLabel = stringResource(id = R.string.label_preview_textField_label),
        dropdownList = stringArrayResource(id = R.array.difficulty),
        showDropdown = true
    ) {}
}