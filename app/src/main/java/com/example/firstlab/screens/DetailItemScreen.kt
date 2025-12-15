package com.example.firstlab.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.firstlab.R
import com.example.firstlab.data.SessionManager
import com.example.firstlab.data.getPersonajeById
import com.example.firstlab.ui.components.HabilidadTag
import com.example.firstlab.ui.theme.FavoriteRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItemScreen(personajeId: Int, modifier: Modifier = Modifier) {
    val personaje = remember(personajeId) { getPersonajeById(personajeId) }

    if (personaje == null) {
        Text("Error: Personaje no encontrado.", modifier = Modifier.padding(16.dp))
        return
    }

    // 💡 PASO 1: Leer el estado de la sesión y del favorito
    val isLoggedIn = SessionManager.isLoggedIn
    val isFavoriteNow = SessionManager.isFavorite(personajeId) // <-- Lee el estado actual

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(personaje.nombre) }) },

        // Botón de Favorito FAB
        floatingActionButton = {
            Button(
                // 💡 PASO 2: Conectar la acción al SessionManager
                onClick = {
                    SessionManager.toggleFavorite(personajeId)
                },
                // 💡 PASO 3: Deshabilitar si no ha iniciado sesión
                enabled = isLoggedIn,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // 💡 PASO 4: Usar el estado actual para definir el icono
                Icon(
                    imageVector = if (isFavoriteNow) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Añadir a favoritos",
                    // Cambiar el color si está deshabilitado
                    tint = if (isLoggedIn) FavoriteRed else MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.width(8.dp))
                Text(if (isFavoriteNow) "Quitar Favorito" else "Añadir a Favoritos")
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = personaje.imagenResourceId), // <-- Usar el nuevo ID
                contentDescription = "Imagen de ${personaje.nombre}",
                modifier = Modifier.size(150.dp).padding(bottom = 8.dp)
            )

            Text(
                text = "${personaje.rol} - ${personaje.posicion}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = personaje.descripcionCorta,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(24.dp))
            Divider()
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Habilidades",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(12.dp))

            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                HabilidadTag(habilidad = personaje.habilidadQ, tipo = 'Q')
                Spacer(Modifier.height(8.dp))
                HabilidadTag(habilidad = personaje.habilidadW, tipo = 'W')
                Spacer(Modifier.height(8.dp))
                HabilidadTag(habilidad = personaje.habilidadE, tipo = 'E')
                Spacer(Modifier.height(8.dp))
                HabilidadTag(habilidad = personaje.habilidadR, tipo = 'R')
            }
        }
    }
}