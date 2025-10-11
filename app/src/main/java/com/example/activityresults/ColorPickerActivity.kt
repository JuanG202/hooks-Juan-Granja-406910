package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class ColorPickerActivity : ComponentActivity() {

    companion object {
        const val RED = 0xFFFF0000L
        const val ORANGE = 0xFFFFA500L
        const val YELLOW = 0xFFFFFF00L
        const val GREEN = 0xFF00FF00L
        const val BLUE = 0xFF0000FFL
        const val INDIGO = 0xFF4B0082L
        const val VIOLET = 0xFF8A2BE2L
    }

    private fun setRainbowColor(color: Long, colorName: String) {
        Intent().let { pickedColorIntent ->
            pickedColorIntent.putExtra(MainActivity.RAINBOW_COLOR_NAME, colorName)
            pickedColorIntent.putExtra(MainActivity.RAINBOW_COLOR, color)
            setResult(RESULT_OK, pickedColorIntent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityResultsTheme {
                ColorPickerScreen()
            }
        }
    }

    @Composable
    private fun ColorPickerScreen() {
        val clickHandler = { color: Long, colorName: String ->
            setRainbowColor(color, colorName)
        }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.header_text_picker),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                )

                RainbowColor(RED.toLong(), getString(R.string.red), clickHandler)
                RainbowColor(ORANGE.toLong(), getString(R.string.orange), clickHandler)
                RainbowColor(YELLOW.toLong(), getString(R.string.yellow), clickHandler)
                RainbowColor(GREEN.toLong(), getString(R.string.green), clickHandler)
                RainbowColor(BLUE.toLong(), getString(R.string.blue), clickHandler)
                RainbowColor(INDIGO.toLong(), getString(R.string.indigo), clickHandler)
                RainbowColor(VIOLET.toLong(), getString(R.string.violet), clickHandler)

                Text(
                    text = stringResource(id = R.string.footer_text_picker),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun RainbowColor(color: Long, colorName: String, onButtonClick: (Long, String) -> Unit) {
    Button(
        onClick = { onButtonClick(color, colorName) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(color),
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = colorName,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
