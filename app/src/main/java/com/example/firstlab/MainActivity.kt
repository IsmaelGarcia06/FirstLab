package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.firstlab.navigation.AppNavHost
import com.example.firstlab.ui.theme.FirstLabTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FirstLabTheme {
                MainAppContainer()
            }
        }
    }
}

@Composable
fun MainAppContainer() {
    val navController = rememberNavController()

    // Estado para mantener la selección en el Master-Detail (persiste con rememberSaveable)
    var selectedItemId by rememberSaveable { mutableIntStateOf(-1) } // -1 = Sin selección

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background // <-- AÑADE ESTA LÍNEA
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            selectedItemId = selectedItemId,
            onItemSelected = { selectedItemId = it },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
