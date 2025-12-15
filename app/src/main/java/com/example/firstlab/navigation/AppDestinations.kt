package com.example.firstlab.navigation

sealed class Destination(val route: String) {
    // Pantallas Principales
    data object ElemList : Destination("personaje_list")
    data object FavList : Destination("fav_list")
    data object Profile : Destination("profile")
    data object About : Destination("about") // Pantalla ya creada

    // Pantallas de Detalle
    data object DetailItem : Destination("detail_personaje/{personajeId}") {
        fun createRoute(personajeId: Int) = "detail_personaje/$personajeId"
    }
    data object DetailFav : Destination("detail_fav/{personajeId}") {
        fun createRoute(personajeId: Int) = "detail_fav/$personajeId"
    }
}
