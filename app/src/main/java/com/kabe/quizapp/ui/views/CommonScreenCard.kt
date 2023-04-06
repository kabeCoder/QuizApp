package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(MaterialTheme.spacing.customSpacingTwo)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
                .height(600.dp)
                .width(300.dp)
        ) {}

        Box(
            modifier = Modifier
                .offset(y = MaterialTheme.spacing.extraSmall)
                .zIndex(-1f)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    clip = false
                )
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .height(598.dp)
                .width(304.dp)
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun CommonScreenCardPreview() {
    CommonScreenCard()
}

