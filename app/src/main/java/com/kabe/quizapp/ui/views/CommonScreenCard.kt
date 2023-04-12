package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun CommonScreenCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ConstraintLayout(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .padding(MaterialTheme.spacing.customSpacingTwo)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                content = content
            )
        }

        Box(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .offset(y = 5.dp)
                .zIndex(-1f)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(15.dp),
                    clip = false,
                )
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                )
                .fillMaxSize()

        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun CommonScreenCardPreview() {
    CommonScreenCard {}
}

