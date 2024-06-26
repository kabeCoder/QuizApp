package com.kabe.quizapp.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    buttonName: String,
    textStyle: TextStyle,
    buttonColor: ButtonColors,
    backgroundOffset: Dp = MaterialTheme.spacing.small,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .padding(
                    start = 3.dp,
                    end = 3.dp
                )
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = buttonColor
        ) {
            Text(
                text = buttonName,
                modifier = Modifier.padding(4.dp),
                style = textStyle
            )
        }
        Box(
            modifier = Modifier
                .offset(y = backgroundOffset)
                .zIndex(-1f)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(25.dp),
                    clip = false,
                )
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(25.dp)
                )
                .height(48.dp)
                .fillMaxWidth()

        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.label_transparent_shadow),
                    color = Color.Transparent,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonButtonPreview() {
    CommonButton(
        buttonName = stringResource(id = R.string.label_preview_button),
        textStyle = MaterialTheme.typography.h5.copy(
            fontSize = 14.sp,
            color = White,
            fontWeight = FontWeight.W600
        ),
        buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
        onClick = {}
    )
}