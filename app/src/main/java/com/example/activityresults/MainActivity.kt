package com.example.activityresults

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
        const val TRANSPARENT = 0x00FFFFFF
    }

    private var rainbowColor by mutableStateOf(Color(TRANSPARENT))
    private var colorName by mutableStateOf("")
    private var colorMessage by mutableStateOf("")

    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            val data = activityResult.data
            val colorLong = data?.getLongExtra(RAINBOW_COLOR, TRANSPARENT.toLong()) ?: TRANSPARENT.toLong()
            rainbowColor = Color(colorLong)
            colorName = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
            colorMessage = getString(R.string.color_chosen_message, colorName)
        }

        setContent {
            val context = LocalContext.current
            ActivityResultsTheme {
                MainScreen(rainbowColor, colorMessage, context, startForResult)
            }
        }
    }
}

@Composable
fun MainScreen(
    backgroundColor: Color,
    colorMessage: String,
    context: Context,
    startForResult: ActivityResultLauncher<Intent>
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = stringResource(R.string.header_text_main),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    val intent = Intent(context, ColorPickerActivity::class.java)
                    startForResult.launch(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = stringResource(R.string.submit_button_text))
            }

            TextWithBackgroundColor(backgroundColor, colorMessage)
        }
    }
}

@Composable
fun TextWithBackgroundColor(backgroundColor: Color, colorMessage: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(80.dp)
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Text(
            text = colorMessage,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
