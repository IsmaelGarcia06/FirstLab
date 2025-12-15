package com.example.firstlab.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


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
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Personajes : BottomNavItem(Destination.ElemList.route, Icons.Filled.List, "Personajes")
    data object Favoritos : BottomNavItem(Destination.FavList.route, Icons.Filled.Star, "Favoritos")
    data object Perfil : BottomNavItem(Destination.Profile.route, Icons.Filled.AccountCircle, "Perfil")
    data object AcercaDe : BottomNavItem(Destination.About.route, Icons.Filled.Info, "Acerca de")
}