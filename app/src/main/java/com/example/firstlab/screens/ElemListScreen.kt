package com.example.firstlab.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.getDummyPersonajes
import com.example.firstlab.components.PersonajeCard
import com.example.firstlab.data.SessionManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(
    onItemClick: (personajeId: Int, navigateToDetail: Boolean) -> Unit,
    // ¡Añadir este parámetro!
    modifier: Modifier = Modifier // <--- CORRECCIÓN
) {
    val currentFavorites = SessionManager.favoriteIds
    val personajes = getDummyPersonajes()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("WikiLol - Personajes") }) },
        // Aplicar el modifier al Scaffold, que es el contenedor principal
        modifier = modifier.fillMaxSize() // <--- Aplicamos el modifier aquí
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(personajes, key = { it.id }) { personaje ->
                PersonajeCard(
                    personaje = personaje,

                    onPersonajeClick = { onItemClick(personaje.id, true) },

                    onFavoriteClick = {
                        SessionManager.toggleFavorite(personaje.id)
                    }
                )
            }
        }
    }
}

@Composable
fun ElemListMaster(
    onItemSelected: (Int) -> Unit
) {
    val personajes = getDummyPersonajes()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Personajes",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            Divider()
        }
        items(personajes) { personaje ->
            PersonajeCard(
                personaje = personaje,
                onPersonajeClick = { onItemClick(personaje.id) },

                onFavoriteClick = {
                    SessionManager.toggleFavorite(personaje.id)
                }
            )
        }
    }
}

private fun LazyItemScope.onItemClick(id: Int) {
    TODO("Not yet implemented")
}
