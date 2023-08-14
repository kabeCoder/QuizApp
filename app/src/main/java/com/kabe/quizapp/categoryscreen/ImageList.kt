package com.kabe.quizapp.categoryscreen


import androidx.compose.ui.graphics.Color
import com.kabe.quizapp.R
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.Blue2
import com.kabe.quizapp.ui.theme.Blue3
import com.kabe.quizapp.ui.theme.Blue4
import com.kabe.quizapp.ui.theme.Gray2
import com.kabe.quizapp.ui.theme.Gray3
import com.kabe.quizapp.ui.theme.Green1
import com.kabe.quizapp.ui.theme.Green2
import com.kabe.quizapp.ui.theme.Green3
import com.kabe.quizapp.ui.theme.Green4
import com.kabe.quizapp.ui.theme.Green5
import com.kabe.quizapp.ui.theme.Orange
import com.kabe.quizapp.ui.theme.Pink
import com.kabe.quizapp.ui.theme.Purple1
import com.kabe.quizapp.ui.theme.Purple2
import com.kabe.quizapp.ui.theme.Purple3
import com.kabe.quizapp.ui.theme.RedOrange1
import com.kabe.quizapp.ui.theme.RedOrange2
import com.kabe.quizapp.ui.theme.RedOrange3
import com.kabe.quizapp.ui.theme.RedOrange4
import com.kabe.quizapp.ui.theme.YellowOrange1
import com.kabe.quizapp.ui.theme.YellowOrange2
import com.kabe.quizapp.ui.theme.YellowOrange3
import com.kabe.quizapp.ui.theme.YellowOrange4
import com.kabe.quizapp.ui.theme.YellowOrange5

data class ImageItems(
    val image: Int,
    val imageLabel: Int,
    val imageLabelColor: Color
)

val imageIcons = listOf(
    ImageItems(
        image = R.drawable.ic_any,
        imageLabel = R.string.label_any_category,
        imageLabelColor = Pink
    ),
    ImageItems(
        image = R.drawable.ic_general_knowledge,
        imageLabel = R.string.label_general_knowledge,
        imageLabelColor = Blue1.copy(alpha = 0.8f)
    ),
    ImageItems(
        image = R.drawable.ic_books,
        imageLabel = R.string.label_entertainment_books,
        imageLabelColor = YellowOrange1
    ),
    ImageItems(
        image = R.drawable.ic_film,
        imageLabel = R.string.label_entertainment_film,
        imageLabelColor = Blue2
    ),
    ImageItems(
        image = R.drawable.ic_music,
        imageLabel = R.string.label_entertainment_music,
        imageLabelColor = Purple1
    ),
    ImageItems(
        image = R.drawable.ic_musicals_and_theaters,
        imageLabel = R.string.label_entertainment_musicals_and_theatres,
        imageLabelColor = Green1
    ),
    ImageItems(
        image = R.drawable.ic_television,
        imageLabel = R.string.label_entertainment_television,
        imageLabelColor = YellowOrange2
    ),
    ImageItems(
        image = R.drawable.ic_video_games,
        imageLabel = R.string.label_entertainment_video_games,
        imageLabelColor = Blue3
    ),
    ImageItems(
        image = R.drawable.ic_board_games,
        imageLabel = R.string.label_entertainment_board_games,
        imageLabelColor = Purple2
    ),
    ImageItems(
        image = R.drawable.ic_science_and_nature,
        imageLabel = R.string.label_science_and_nature,
        imageLabelColor = Green2
    ),
    ImageItems(
        image = R.drawable.ic_computers,
        imageLabel = R.string.label_science_computer,
        imageLabelColor = Orange
    ),
    ImageItems(
        image = R.drawable.ic_mathematics,
        imageLabel = R.string.label_science_mathematics,
        imageLabelColor = Green3
    ),
    ImageItems(
        image = R.drawable.ic_mythology,
        imageLabel = R.string.label_mythology,
        imageLabelColor = RedOrange1
    ),
    ImageItems(
        image = R.drawable.ic_sports,
        imageLabel = R.string.label_sports,
        imageLabelColor = Green4
    ),
    ImageItems(
        image = R.drawable.ic_geography,
        imageLabel = R.string.label_geography,
        imageLabelColor = RedOrange2
    ),
    ImageItems(
        image = R.drawable.ic_history,
        imageLabel = R.string.label_history,
        imageLabelColor = Green5
    ),
    ImageItems(
        image = R.drawable.ic_politics,
        imageLabel = R.string.label_politics,
        imageLabelColor = RedOrange3
    ),
    ImageItems(
        image = R.drawable.ic_arts,
        imageLabel = R.string.label_arts,
        imageLabelColor = Gray2
    ),
    ImageItems(
        image = R.drawable.ic_celebrities,
        imageLabel = R.string.label_celebrities,
        imageLabelColor = YellowOrange3
    ),
    ImageItems(
        image = R.drawable.ic_animals,
        imageLabel = R.string.label_animals,
        imageLabelColor = Gray3
    ),
    ImageItems(
        image = R.drawable.ic_vehicles,
        imageLabel = R.string.label_vehicles,
        imageLabelColor = YellowOrange4
    ),
    ImageItems(
        image = R.drawable.ic_comics,
        imageLabel = R.string.label_entertainment_comics,
        imageLabelColor = RedOrange4
    ),
    ImageItems(
        image = R.drawable.ic_gadgets,
        imageLabel = R.string.label_science_gadgets,
        imageLabelColor = Blue4
    ),
    ImageItems(
        image = R.drawable.ic_anime,
        imageLabel = R.string.label_entertainment_japanese_anime_and_manga,
        imageLabelColor = Purple3
    ),
    ImageItems(
        image = R.drawable.ic_cartoon,
        imageLabel = R.string.label_entertainment_cartoon_and_animation,
        imageLabelColor = YellowOrange5
    )
)
