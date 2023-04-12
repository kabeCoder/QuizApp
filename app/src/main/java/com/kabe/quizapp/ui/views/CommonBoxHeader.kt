package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kabe.quizapp.ui.theme.Blue

@Composable
fun CommonBoxHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Blue)
            .height(200.dp)
            .fillMaxWidth()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun CommonBoxHeaderPreview() {
    CommonBoxHeader()
}