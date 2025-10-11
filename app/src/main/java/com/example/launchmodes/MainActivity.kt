package com.example.launchmodes


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Main Activity", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                startActivity(Intent(this@MainActivity, StandardActivity::class.java))
            }) {
                Text(text = getString(R.string.launch_standard))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                startActivity(Intent(this@MainActivity, SingleTopActivity::class.java))
            }) {
                Text(text = getString(R.string.launch_single_top))
            }
        }
    }
}
