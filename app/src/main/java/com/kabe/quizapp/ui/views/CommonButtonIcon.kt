package com.kabe.quizapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.DarkBlue
import com.kabe.quizapp.ui.theme.White

@Composable
fun CommonButtonIcon(
    modifier: Modifier = Modifier,
    buttonIcon: Painter,
    buttonColor: ButtonColors,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .width(45.dp)
                .height(40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = buttonColor
        ) {
            Icon(
                painter = buttonIcon,
                contentDescription = stringResource(id = R.string.label_icon_button_description),
                modifier = Modifier
                    .width(35.dp)
                    .height(30.dp),
                tint = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonButtonIconPreview() {
    CommonButtonIcon(
        buttonIcon = painterResource(id = R.drawable.ic_back),
        buttonColor = ButtonDefaults.buttonColors(backgroundColor = DarkBlue),
        onClick = {}
    )
}