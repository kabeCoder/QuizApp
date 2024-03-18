package com.kabe.quizapp.quizscreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.Orange
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun QuestionItemBoxCount(
    modifier: Modifier = Modifier,
    numberOfItems: Int,
    currentQuestionIndex: Int?,
    answeredQuestions: List<Boolean>
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            content = {
                itemsIndexed(items = (0 until numberOfItems).chunked(15)) { rowIndex, chunkedItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                    ) {
                        chunkedItems.forEachIndexed { index, _ ->
                            val questionIndex = rowIndex * 15 + index
                            if (questionIndex < numberOfItems) {
                                val boxColor = when {
                                    currentQuestionIndex == questionIndex -> Color.Yellow
                                    questionIndex < currentQuestionIndex!! -> {
                                        if (answeredQuestions[questionIndex]) Color.Green else Color.Red
                                    }

                                    else -> Color.White
                                }

                                Box(
                                    modifier = Modifier
                                        .background(boxColor)
                                        .width(15.dp)
                                        .height(3.dp)
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionItemBoxCountPreview() {
    //QuestionItemBoxCount(numberOfItems = 5, selectedAnswerIndex = 0, correctAnswerIndex = 0)
}