package com.example.firstlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstlab.data.getPersonajeById
import com.example.firstlab.model.Comentario
import com.example.firstlab.ui.theme.CommentBubble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFavScreen(personajeId: Int) {
    val personaje = remember(personajeId) { getPersonajeById(personajeId) }

    if (personaje == null || !personaje.isFavorite) {
        Text("Personaje no encontrado o no marcado como favorito.", modifier = Modifier.padding(16.dp))
        return
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Comentarios de ${personaje.nombre}") }) },
        // FAB para incluir nuevos comentarios
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Acción para añadir comentario */ },
                icon = { Icon(Icons.Filled.Comment, "Añadir") },
                text = { Text("Añadir Comentario") },
                containerColor = MaterialTheme.colorScheme.tertiary // Color personalizado Dorado
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (personaje.comentarios.isEmpty()) {
                Text(
                    text = "Aún no hay comentarios para ${personaje.nombre}.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(personaje.comentarios) { comentario ->
                        ComentarioItem(comentario = comentario)
                    }
                }
            }
        }
    }
}

@Composable
fun ComentarioItem(comentario: Comentario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CommentBubble)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = comentario.userId,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = comentario.texto,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}