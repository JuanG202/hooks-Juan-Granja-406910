package com.example.launchmodes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SingleTopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Single Top Activity", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    startActivity(intent)
                }) {
                    Text(text = "Launch Single Top Again")
                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(onClick = {
                    val intent = Intent(this@SingleTopActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }) {
                    Text(text = "Go Back")
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Toast.makeText(this, "Reused existing SingleTopActivity", Toast.LENGTH_SHORT).show()
    }
}
