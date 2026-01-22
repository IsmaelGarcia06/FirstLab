package com.example.firstlab.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.components.PersonajeCard
import com.example.firstlab.data.getDummyPersonajes
import com.example.firstlab.data.SessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(
    onItemClick: (Int, Boolean) -> Unit, // Recibe ID y si debe navegar
    modifier: Modifier = Modifier
) {
    // 1. Estados para la búsqueda
    var searchQuery by remember { mutableStateOf("") }

    // 2. Filtrado de la lista
    val personajesCompletos = remember { getDummyPersonajes() }
    val personajesFiltrados = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            personajesCompletos
        } else {
            personajesCompletos.filter {
                it.nombre.contains(searchQuery, ignoreCase = true) ||
                        it.rol.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("WikiLol - Personajes") }
                )

                // 3. BUSCADOR CON CORRECCIÓN DE COLORES
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Buscar campeón o rol...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Limpiar")
                            }
                        }
                    },
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    // 🔥 Corrección de colores para Material 3
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    ) { paddingValues ->
        if (personajesFiltrados.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No se encontró ningún campeón con '$searchQuery'")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() + 16.dp,
                    start = 8.dp,
                    end = 8.dp
                )
            ) {
                items(personajesFiltrados, key = { it.id }) { personaje ->
                    PersonajeCard(
                        personaje = personaje,
                        onPersonajeClick = {
                            // Navega al detalle usando la función del AppNavHost
                            onItemClick(personaje.id, true)
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