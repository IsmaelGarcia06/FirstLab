package com.example.firstlab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.ui.theme.FirstLabTheme

class EndGameActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Recuperar los valores enviados desde MainActivity
        val score = intent.getIntExtra(MainActivity.SCORE_KEY, 0)
        val level = intent.getIntExtra(MainActivity.LEVEL_KEY, 0)

        setContent {
            FirstLabTheme {
                Scaffold(
                    topBar = { CenterAlignedTopAppBar(title = { Text("End of Game") }) }
                ) { innerPadding ->
                    EndGameScreen(
                        score = score,
                        level = level,
                        onRestartClick = {
                            // Reiniciar juego
                            val restartIntent = Intent(this, MainActivity::class.java)
                            restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(restartIntent)
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun EndGameScreen(score: Int, level: Int, onRestartClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Final Score: $score")
        Text(text = "Final Level: $level")
        Button(onClick = onRestartClick, modifier = Modifier.padding(top = 16.dp)) {
            Text("Restart Game")
        }
    }
}
