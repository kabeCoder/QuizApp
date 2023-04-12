package com.kabe.quizapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Pink
import com.kabe.quizapp.ui.theme.spacing

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    label: String,
    labelColor: Color,
    iconSize: Dp,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable {
                    onClick.invoke()
                }
                .width(140.dp)
                .height(130.dp),
            contentAlignment = Alignment.Center

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.label_icon_button_description),
                modifier = Modifier
                    .size(iconSize)
                )
                Text(
                    text = label,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small
                        ),
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    maxLines = 3,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 12.sp,
                        color = labelColor,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        }

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
                .width(140.dp)
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.label_icon_button_description),
                    modifier = Modifier
                        .size(iconSize),
                    tint = Color.Transparent
                )
                Text(
                    text = label,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small
                        ),
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    maxLines = 3,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 12.sp,
                        color = Color.Transparent,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(
        painter = painterResource(id = R.drawable.ic_any),
        label = stringResource(id = R.string.label_preview_category_text),
        labelColor = Pink,
        iconSize = 75.dp
    ) {}
}