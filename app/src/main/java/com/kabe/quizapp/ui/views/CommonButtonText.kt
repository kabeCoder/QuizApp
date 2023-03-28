package com.kabe.quizapp.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Blue
import com.kabe.quizapp.ui.theme.White

@Composable
fun CommonButtonText(
    modifier: Modifier = Modifier,
    buttonName: String,
    textStyle: TextStyle,
    buttonColor: ButtonColors,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = buttonColor
        ) {
            Text(
                text = buttonName,
                modifier = Modifier.padding(12.dp),
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonButtonPreview() {
    CommonButtonText(
        buttonName = stringResource(id = R.string.label_preview_button),
        textStyle = MaterialTheme.typography.h5.copy(
            fontSize = 14.sp,
            color = White,
            fontWeight = FontWeight.W600
        ),
        buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue),
        onClick = {}
    )
}