package com.example.firstlab.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.* // Cambiado a .* para incluir mutableStateOf y getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.getDummyPersonajes
import com.example.firstlab.data.SessionManager
import com.example.firstlab.components.PersonajeCard
import com.example.firstlab.model.Personaje // Asegúrate de tener esta importación

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(onItemClick: (Int) -> Unit) {

    val currentFavoriteIds = SessionManager.favoriteIds

    // --- 1. NUEVOS ESTADOS PARA EL DIÁLOGO ---
    var showDialog by remember { mutableStateOf(false) }
    var personajeAEliminar by remember { mutableStateOf<Personaje?>(null) }

    val favoritos = remember(currentFavoriteIds) {
        getDummyPersonajes().filter { personaje ->
            currentFavoriteIds.contains(personaje.id)
        }
    }

    // --- 2. COMPONENTE DE VENTANA EMERGENTE ---
    if (showDialog && personajeAEliminar != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Quitar de favoritos") },
            text = {
                Text("¿Seguro que quieres eliminar a ${personajeAEliminar?.nombre} de tu lista de favoritos?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        personajeAEliminar?.let { SessionManager.toggleFavorite(it.id) }
                        showDialog = false
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Mis Campeones Favoritos") }) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        if (favoritos.isEmpty()) {
            Text(
                text = "No tienes personajes favoritos. ¡Añade algunos!",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        } else {
            LazyColumn(contentPadding = paddingValues) {
                items(favoritos, key = { it.id }) { personaje ->
                    PersonajeCard(
                        personaje = personaje,
                        onPersonajeClick = { },
                        onFavoriteClick = {
                            // --- 3. CAMBIO DE LÓGICA ---
                            // En lugar de borrar, guardamos el personaje y mostramos el diálogo
                            personajeAEliminar = personaje
                            showDialog = true
                        }
                    )
                }
            }
        }
    }
}