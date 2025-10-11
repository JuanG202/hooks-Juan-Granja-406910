package com.example.saveandrestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saveandrestore.ui.theme.SaveAndRestoreTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private var randomNumber by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(RANDOM_NUMBER, 0)
        }

        setContent {
            SaveAndRestoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RandomNumberScreen(
                        randomNumber = randomNumber,
                        onGenerateClick = { randomNumber = generateRandomNumber() }
                    )
                }
            }
        }
    }


    private fun generateRandomNumber(): Int {
        return Random.nextInt(0, 1000)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER, randomNumber)
    }

    companion object {
        private const val RANDOM_NUMBER = "RANDOM_NUMBER"
    }
}

// Composable principal
@Composable
fun RandomNumberScreen(
    randomNumber: Int,
    onGenerateClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onGenerateClick) {
            Text(
                text = stringResource(id = R.string.generate_random_number),
                fontSize = 18.sp
            )
        }

        Text(
            text = stringResource(id = R.string.random_number_message, randomNumber),
            fontSize = 18.sp
        )
    }
}
