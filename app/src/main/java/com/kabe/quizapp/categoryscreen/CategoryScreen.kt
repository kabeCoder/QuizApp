package com.kabe.quizapp.categoryscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.DarkBlue
import com.kabe.quizapp.ui.theme.Pink
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CategoryCard
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonButtonIcon
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CategoryScreen() {
    CategoryScreenView()
}

@Composable
fun CategoryScreenView() {

    val scrollState = rememberScrollState()
    val categories = stringArrayResource(id = R.array.category).toList()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            btnBack,
            textHeader,
            categoriesCard
        ) = createRefs()

        CommonBoxHeader()

        CommonButtonIcon(
            modifier = Modifier
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(btnBack.start)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                ),
            buttonIcon = painterResource(id = R.drawable.ic_back),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = DarkBlue),
            onClick = {}
        )

        Text(
            text = stringResource(id = R.string.label_choose_categories),
            modifier = Modifier
                .constrainAs(textHeader) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                    bottom.linkTo(categoriesCard.top)
                }
                .padding(
                    top = MaterialTheme.spacing.large
                            + MaterialTheme.spacing.extraSmall
                            + MaterialTheme.spacing.customSpacingTwo
                ),
            style = MaterialTheme.typography.h3.copy(
                fontSize = 20.sp,
                color = White,
                fontWeight = FontWeight.W700
            )
        )

        CommonScreenCard(
            modifier = Modifier
                .constrainAs(categoriesCard) {
                    start.linkTo(parent.start)
                    top.linkTo(textHeader.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.extraLarge
                            + MaterialTheme.spacing.large
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                Row {
                    categories.filterIndexed { index, _ -> index % 2 == 0 }.chunked(13)
                        .forEach { chunk ->
                            Column {
                                chunk.forEach { category ->
                                    CategoryCard(
                                        modifier = Modifier
                                            .padding(
                                                start = MaterialTheme.spacing.medium,
                                                top = MaterialTheme.spacing.medium,
                                                bottom = MaterialTheme.spacing.medium,
                                                end = MaterialTheme.spacing.small
                                            ),
                                        painter = painterResource(id = R.drawable.ic_any),
                                        label = category,
                                        labelColor = Pink,
                                        iconSize = 75.dp
                                    ) {}
                                }
                            }
                        }
                    categories.filterIndexed { index, _ -> index % 2 == 1 }.chunked(12)
                        .forEach { chunk ->
                            Column(
                                modifier = Modifier
                                    .padding(
                                        top = MaterialTheme.spacing.large
                                    )
                            ) {
                                chunk.forEach { category ->
                                    CategoryCard(
                                        modifier = Modifier
                                            .padding(
                                                start = MaterialTheme.spacing.small,
                                                top = MaterialTheme.spacing.medium,
                                                bottom = MaterialTheme.spacing.medium,
                                                end = MaterialTheme.spacing.medium
                                            ),
                                        painter = painterResource(id = R.drawable.ic_any),
                                        label = category,
                                        labelColor = Pink,
                                        iconSize = 75.dp
                                    ) {}
                                }
                            }
                        }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}