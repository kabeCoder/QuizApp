package com.kabe.quizapp.ui.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kabe.quizapp.ui.theme.Black

@Composable
fun CommonAlertDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("No Quiz Data") },
            text = { Text("The server did not provide any quiz data. Please reselect trivia questions.") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(
                        "Okay".uppercase(),
                        color = Black,
                    )
                }
            },

        )
    }
}
