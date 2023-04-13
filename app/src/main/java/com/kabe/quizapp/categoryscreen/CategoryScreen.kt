package com.kabe.quizapp.categoryscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.destinations.SetupScreenDestination
import com.kabe.quizapp.destinations.StartScreenDestination
import com.kabe.quizapp.ui.theme.DarkBlue1
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CategoryCard
import com.kabe.quizapp.ui.views.CommonBoxHeader
import com.kabe.quizapp.ui.views.CommonButtonIcon
import com.kabe.quizapp.ui.views.CommonScreenCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CategoryScreen(
    navigator: DestinationsNavigator?
) {
    CategoryScreenView(navigator)
}

@Composable
fun CategoryScreenView(
    navigator: DestinationsNavigator?
) {

    val categoryScreenScrollState = rememberScrollState()
    val context = LocalContext.current

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
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = DarkBlue1),
            onClick = {
                navigator?.popBackStack(
                    route = StartScreenDestination,
                    inclusive = false
                )
            }
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
                    .verticalScroll(categoryScreenScrollState),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    imageIcons.filterIndexed { index, _ -> index % 2 == 0 }.chunked(13)
                        .forEach { chunkedCategoryName ->
                            Column {
                                chunkedCategoryName.forEach { category ->
                                    CategoryCard(
                                        modifier = Modifier
                                            .padding(
                                                top = MaterialTheme.spacing.medium,
                                                bottom = MaterialTheme.spacing.medium,
                                                end = MaterialTheme.spacing.small
                                            ),
                                        painter = painterResource(id = category.image),
                                        label = context.getString(category.imageLabel),
                                        labelColor = category.imageLabelColor,
                                        iconSize = 75.dp
                                    ) { selectedCategory ->
                                        navigator?.navigate(SetupScreenDestination(selectedCategory))
                                    }
                                }
                            }
                        }
                    imageIcons.filterIndexed { index, _ -> index % 2 == 1 }.chunked(12)
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
                                                bottom = MaterialTheme.spacing.medium
                                            ),
                                        painter = painterResource(id = category.image),
                                        label = context.getString(category.imageLabel),
                                        labelColor = category.imageLabelColor,
                                        iconSize = 75.dp
                                    ) { selectedCategory ->
                                        navigator?.navigate(SetupScreenDestination(selectedCategory))
                                    }
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
    CategoryScreen(null)
}