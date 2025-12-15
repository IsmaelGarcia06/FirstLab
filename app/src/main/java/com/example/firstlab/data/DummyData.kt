package com.example.firstlab.data

import com.example.firstlab.R
import com.example.firstlab.model.Comentario
import com.example.firstlab.model.Personaje

fun getDummyPersonajes(): List<Personaje> {
    val comentariosLux = listOf(
        Comentario(1, "Invocador123", "El mejor mid laner para carry!"),
        Comentario(2, "SupportMain", "Su daño es increíble, pero es muy blando.")
    )

    return listOf(
        Personaje(
            id = 1,
            nombre = "Lux",
            rol = "Mago",
            posicion = "Mid",
            descripcionCorta = "La Dama Luminosa, una maga poderosa y explosiva que atrapa y elimina a sus enemigos desde lejos.",
            habilidadQ = "Enlace de luz",
            habilidadW = "Barrera prismática",
            habilidadE = "Singularidad brillante",
            habilidadR = "Rayo final",
            imagenResourceId = R.drawable.lux_icon,
            comentarios = comentariosLux,
            isFavorite = true // Marcar como favorito para pruebas
        ),
        Personaje(
            id = 2,
            nombre = "Braum",
            rol = "Tanque",
            posicion = "Support",
            descripcionCorta = "El corazón del Freljord, un granjero amistoso convertido en un defensor inquebrantable de sus aliados.",
            habilidadQ = "Mordisco invernal",
            habilidadW = "Detrás de mí",
            habilidadE = "Inquebrantable",
            habilidadR = "Fisura glacial",
            imagenResourceId = R.drawable.braum_icon,
            comentarios = emptyList(),
            isFavorite = false
        ),
        Personaje(
            id = 3,
            nombre = "Jinx",
            rol = "Tiradora",
            posicion = "ADC",
            descripcionCorta = "Una lunática con gran arsenal explosivo, especializada en causar caos a larga distancia.",
            habilidadQ = "¡Cambio!",
            habilidadW = "Chispazo",
            habilidadE = "Mordiscones",
            habilidadR = "Supermegacohete de la muerte",
            imagenResourceId = R.drawable.jinx_icon,
            comentarios = emptyList(),
            isFavorite = true
        )
    )
}

fun getPersonajeById(id: Int): Personaje? {
    return getDummyPersonajes().find { it.id == id }
}