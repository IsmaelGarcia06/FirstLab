package com.example.firstlab.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var isLoggedIn by remember { mutableStateOf(false) } // Estado de la sesión

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Mi Perfil - WikiLol") }) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoggedIn) {
                Text(
                    text = "Bienvenido, Invocador!",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Nivel de cuenta: 300",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(32.dp))
                Button(onClick = { isLoggedIn = false }) {
                    Text("Cerrar Sesión (Logout)")
                }
            } else {
                Text(
                    text = "Inicia sesión para gestionar tus favoritos.",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(Modifier.height(32.dp))
                Button(onClick = { isLoggedIn = true }) {
                    Text("Iniciar Sesión (Login)")
                }
            }
        }
    }
}