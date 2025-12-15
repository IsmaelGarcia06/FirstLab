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
import com.example.firstlab.components.PersonajeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(onItemClick: (Int) -> Unit) {
    val favoritos = remember { getDummyPersonajes().filter { it.isFavorite } }

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
                items(favoritos) { personaje ->
                    // El botón para eliminar de favoritos está implícito en la lógica de onFavoriteClick
                    PersonajeCard(
                        personaje = personaje,
                        onPersonajeClick = { onItemClick(personaje.id) },
                        onFavoriteClick = { /* Lógica de ELIMINAR de favorito */ }
                    )
                }
            }
        }
    }
}