package com.kabe.quizapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Black
import com.kabe.quizapp.ui.theme.Pink

@Composable
fun CategoryCard(
    painter: Painter,
    label: String,
    labelColor: Color,
    onClick: () -> Unit

) {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    2.dp
                )
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
                .width(130.dp)
                .height(120.dp)
                .clickable {
                    onClick.invoke()
                },
            contentAlignment = Alignment.Center

        ) {
            Column {
                Icon(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .offset(x = 18.dp)
                        .size(75.dp),
                    tint = Pink
                )
                Text(
                    text = label,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 14.sp,
                        color = labelColor,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(y = 5.dp)
                .zIndex(-1f)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(10.dp),
                    clip = false
                )
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .width(134.dp)
                .height(120.dp)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.label_transparent_shadow),
                    color = Color.Transparent,
                    modifier = Modifier.padding(12.dp)
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
        labelColor = Pink
    ) {}
}