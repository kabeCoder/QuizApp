package com.kabe.quizapp.startscreen

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.kabe.quizapp.R
import com.kabe.quizapp.destinations.CategoryScreenDestination
import com.kabe.quizapp.ui.theme.Blue1
import com.kabe.quizapp.ui.theme.QuizAppTheme
import com.kabe.quizapp.ui.theme.White
import com.kabe.quizapp.ui.theme.spacing
import com.kabe.quizapp.ui.views.CommonButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun StartScreen(
    navigator: DestinationsNavigator?
) {
    StartScreenView(navigator = navigator)
}

@Composable
fun StartScreenView(
    navigator: DestinationsNavigator?
) {
    val context = LocalContext.current
    val bluetoothManager: BluetoothManager = context.getSystemService(BluetoothManager::class.java)
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

    val enableBluetoothLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("StartScreen", "Allowed by user")
            // Bluetooth was enabled
            // You can handle further actions here if needed
        } else {
            Log.d("StartScreen", "Declined by user")
            // User declined to enable Bluetooth
            // Handle this situation as needed
        }
    }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            playButton,
            versusButton,
            exitButton
        ) = createRefs()

        CommonButton(
            modifier = Modifier.constrainAs(playButton) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            },
            buttonName = stringResource(id = R.string.label_play),
            textStyle = MaterialTheme.typography.h4.copy(
                fontSize = 14.sp,
                color = White,
                fontWeight = FontWeight.W600
            ),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
            backgroundOffset = MaterialTheme.spacing.extraSmall
        ) {
            navigator?.navigate(CategoryScreenDestination)
        }

        CommonButton(
            modifier = Modifier.constrainAs(versusButton) {
                top.linkTo(playButton.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            },
            buttonName = stringResource(id = R.string.label_versus),
            textStyle = MaterialTheme.typography.h4.copy(
                fontSize = 14.sp,
                color = White,
                fontWeight = FontWeight.W600
            ),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
            backgroundOffset = MaterialTheme.spacing.extraSmall
        ) {
            if (bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                enableBluetoothLauncher.launch(enableBtIntent)
            }
        }

        CommonButton(
            modifier = Modifier.constrainAs(exitButton) {
                top.linkTo(versusButton.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            },
            buttonName = stringResource(id = R.string.label_exit),
            textStyle = MaterialTheme.typography.h4.copy(
                fontSize = 14.sp,
                color = White,
                fontWeight = FontWeight.W600
            ),
            buttonColor = ButtonDefaults.buttonColors(backgroundColor = Blue1),
            backgroundOffset = MaterialTheme.spacing.extraSmall
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    QuizAppTheme {
        StartScreen(null)
    }
}