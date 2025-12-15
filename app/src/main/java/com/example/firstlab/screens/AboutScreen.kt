package com.example.firstlab.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Acerca de WikiLol") }) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Puedes usar el Icono del FAB o un Icono de Mailbox como en la imagen antigua
            //
            Spacer(Modifier.height(32.dp))
            Text(text = "Nombre de la Aplicación: WikiLol")
            Spacer(Modifier.height(8.dp))
            Text(text = "Temática: Información / Personajes de Videojuego")
            Spacer(Modifier.height(8.dp))
            Text(text = "Descripción: Esta aplicación permite consultar información detallada sobre campeones de League of Legends.")
            Spacer(Modifier.height(8.dp))
            Text(text = "Versión: 1.0")
        }
    }
}