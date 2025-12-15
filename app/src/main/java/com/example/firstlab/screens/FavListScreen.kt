package com.example.firstlab.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.getDummyPersonajes
import com.example.firstlab.data.SessionManager // <-- ¡IMPORTANTE!
import com.example.firstlab.components.PersonajeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(onItemClick: (Int) -> Unit) {

    // 💡 PASO 1: Observar la lista de IDs favoritos del SessionManager
    val currentFavoriteIds = SessionManager.favoriteIds

    // 💡 PASO 2: Obtener y filtrar la lista de personajes.
    // Esta lista se recalcula CADA VEZ que currentFavoriteIds cambia.
    val favoritos = remember(currentFavoriteIds) {
        getDummyPersonajes().filter { personaje ->
            currentFavoriteIds.contains(personaje.id)
        }
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

                        onPersonajeClick = {
                        },

                        onFavoriteClick = {
                            SessionManager.toggleFavorite(personaje.id)
                        }
                    )
                }
            }
        }
    }
}