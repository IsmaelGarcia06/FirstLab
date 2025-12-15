package com.example.firstlab.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons // Sintaxis correcta
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstlab.R
import com.example.firstlab.model.Personaje // Asumiendo que está aquí
import com.example.firstlab.ui.theme.FavoriteRed // Asumiendo que está aquí

@Composable
fun PersonajeCard(
    personaje: Personaje,
    onPersonajeClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    // 💡 Usa MaterialTheme.colorScheme.surface (oscuro en tu tema personalizado)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable(onClick = onPersonajeClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Carga la imagen local del modelo de datos
            Image(
                painter = painterResource(id = personaje.imagenResourceId),
                contentDescription = "Icono de ${personaje.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = personaje.nombre,
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Rol: ${personaje.rol} | Posición: ${personaje.posicion}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

            // Botón de Favorito
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (personaje.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Añadir a favoritos",
                    // Usa el color personalizado FavoriteRed
                    tint = if (personaje.isFavorite)
                        MaterialTheme.colorScheme.secondary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant

                )
            }
        }
    }
}