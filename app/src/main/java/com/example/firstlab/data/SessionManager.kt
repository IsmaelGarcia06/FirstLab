package com.example.firstlab.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object SessionManager {

    var isLoggedIn by mutableStateOf(false)
        private set // El estado solo se cambia con login/logout

    var favoriteIds by mutableStateOf(setOf<Int>())
        private set

    private var savedFavorites = setOf<Int>()


    fun login() {

        // Simulamos la carga: restauramos los favoritos guardados anteriormente
        favoriteIds = savedFavorites.toSet()
        isLoggedIn = true
        println("Sesión iniciada. Favoritos cargados: $favoriteIds")
    }

    fun logout() {
        savedFavorites = favoriteIds.toSet()

        favoriteIds = setOf()
        isLoggedIn = false
        println("Sesión cerrada. Favoritos guardados: $savedFavorites")
    }

    fun toggleFavorite(personajeId: Int) {
        if (!isLoggedIn) {
            // Requisito: Si no está logueado, no se puede marcar como favorito.
            println("ERROR: No se puede marcar/desmarcar si no hay sesión iniciada.")
            return
        }

        favoriteIds = if (favoriteIds.contains(personajeId)) {
            favoriteIds - personajeId // Desmarcar
        } else {
            favoriteIds + personajeId // Marcar
        }

        savedFavorites = favoriteIds.toSet()
    }

    fun isFavorite(personajeId: Int): Boolean {
        return favoriteIds.contains(personajeId)
    }
}