// screens/ProfileScreen.kt (Código simplificado)

package com.example.firstlab.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.SessionManager // <-- Importar el gestor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    // 💡 Lee el estado observable
    val isLoggedIn = SessionManager.isLoggedIn

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Perfil de Usuario") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Muestra el estado actual
            Text(
                text = if (isLoggedIn) "¡Bienvenido! Sesión Activa" else "Sesión Cerrada. Por favor, inicia sesión.",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(32.dp))

            // Botón de Login/Logout
            Button(
                onClick = {
                    if (isLoggedIn) {
                        SessionManager.logout()
                    } else {
                        SessionManager.login()
                    }
                },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = if (isLoggedIn) "Cerrar Sesión" else "Iniciar Sesión"
                )
            }
        }
    }
}