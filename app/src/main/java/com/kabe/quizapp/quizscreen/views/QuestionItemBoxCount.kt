package com.kabe.quizapp.quizscreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.Green6
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun QuestionItemBoxCount(
    modifier: Modifier = Modifier,
    numberOfItems: Int,
    color: Color
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
        ) {
            items(numberOfItems) {
                Box(
                    modifier = Modifier
                        .padding(
                            end = MaterialTheme.spacing.extraSmall
                        )
                        .background(color)
                        .width(15.dp)
                        .height(3.dp)
                )
            }
        }

    }
    
}

@Preview(showBackground = true)
@Composable
fun QuestionItemBoxCountPreview() {
    QuestionItemBoxCount(
        numberOfItems = 5, color = Green6 )
}