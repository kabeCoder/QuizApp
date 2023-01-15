package com.kabe.quizapp.ui.presentation.playscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.kabe.quizapp.R

@Destination
@Composable
fun SetUpScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (dropDownMenu) = createRefs()

        DropDownMenu()
    }
}

@Composable
fun DropDownMenu() {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    val mExpanded = remember { mutableStateOf(false) }

    // Create a list of cities
    val selectDifficulty = stringArrayResource(id = R.array.difficulty)

    // Create a string value to store the selected city
    val mSelectedText = remember { mutableStateOf("") }

    val mTextFieldSize = remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText.value,
            onValueChange = { mSelectedText.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize.value = coordinates.size.toSize()
                },
            label = { Text("Select Difficulty") },
            enabled = false,
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { mExpanded.value = !mExpanded.value })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded.value,
            onDismissRequest = { mExpanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })
        ) {
            selectDifficulty.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText.value = label
                    mExpanded.value = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayScreen() {
    QuizAppTheme {
        SetUpScreen()
    }
}