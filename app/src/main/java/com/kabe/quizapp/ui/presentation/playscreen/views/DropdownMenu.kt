package com.kabe.quizapp.ui.presentation.playscreen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownMenu(
    selectTitle: String,
    selectedList: Array<String>,
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    initialSelected: MutableState<String>,
    textFieldSize: MutableState<Size>,
    onValueSelected: (String) -> Unit
) {

    val icon = if (isExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier.padding(20.dp)) {

        OutlinedTextField(
            value = initialSelected.value,
            onValueChange = { initialSelected.value = it },
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize.value = coordinates.size.toSize()
                },
            label = { Text(selectTitle) },
            enabled = false,
            trailingIcon = {
                Icon(icon, "contentDescription",
                    modifier.clickable { isExpanded.value = !isExpanded.value })
            }
        )

        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = modifier
                .width(with(LocalDensity.current) { textFieldSize.value.width.toDp() }),
        ) {
            selectedList.forEach { label ->
                DropdownMenuItem(onClick = {
                    initialSelected.value = label
                    isExpanded.value = false
                    onValueSelected.invoke(initialSelected.value)
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropdownMenu() {
    DropDownMenu(
        selectTitle = "Dropdown",
        selectedList = arrayOf("", ""),
        isExpanded = remember {
            mutableStateOf(false)
        },
        initialSelected = remember {
            mutableStateOf("")
        },
        textFieldSize = remember {
            mutableStateOf(Size.Zero)
        },
        onValueSelected = {

        }
    )
}