package com.example.firstlab.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.getDummyPersonajes
import com.example.firstlab.components.PersonajeCard

// 1. Variante COMPACTA: Utilizada por NavHost para navegación móvil
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(
    onItemClick: (Int) -> Unit
) {
    val personajes = getDummyPersonajes()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("WikiLol - Personajes") }) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(personajes) { personaje ->
                PersonajeCard(
                    personaje = personaje,
                    onPersonajeClick = { onItemClick(personaje.id) },
                    onFavoriteClick = { /* Lógica de favorito */ }
                )
            }
        }
    }
}

// 2. Variante MASTER: Utilizada en TwoPaneLayout para Master-Detail (Medium/Expanded)
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
                onPersonajeClick = { onItemSelected(personaje.id) }, // <--- CLAVE: Solo actualiza el estado
                onFavoriteClick = { /* Lógica de favorito */ }
            )
        }
    }
}