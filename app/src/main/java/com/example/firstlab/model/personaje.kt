package com.example.firstlab.model

import androidx.compose.runtime.Immutable

@Immutable
data class Personaje(
    val id: Int,
    val nombre: String,
    val rol: String,
    val posicion: String,
    val descripcionCorta: String,
    val habilidadQ: String,
    val habilidadW: String,
    val habilidadE: String,
    val habilidadR: String, // Habilidad definitiva
    val imagenResourceId: Int,
    val comentarios: List<Comentario> = emptyList(),
    var isFavorite: Boolean = false
)

@Immutable
data class Comentario(
    val id: Int,
    val userId: String,
    val texto: String
)