package com.example.firstlab.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.firstlab.screens.* // Importa todas las pantallas

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    selectedItemId: Int,
    onItemSelected: (Int) -> Unit
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val windowSize = getWindowSize()

    // 💡 Requisito: Master-Detail para listas/detalles en Medium/Expanded
    val isMasterDetailRoute = (currentRoute == Destination.ElemList.route || currentRoute?.startsWith("detail_personaje") == true)
            && (windowSize != WindowSize.Compact)

    if (isMasterDetailRoute) {
        // Variante para tamaño Medio y Expandido (Master-Detail)
        TwoPaneLayout(
            selectedItemId = selectedItemId,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
    } else {
        // Variante para tamaño Compacto (Móvil) y el resto de pantallas
        NavHost(
            navController = navController,
            startDestination = Destination.ElemList.route,
            modifier = modifier
        ) {
            composable(Destination.ElemList.route) {
                ElemListScreen(
                    onItemClick = { itemId -> navController.navigate(Destination.DetailItem.createRoute(itemId)) }
                )
            }
            composable(Destination.DetailItem.route, arguments = listOf(
                navArgument("personajeId") { type = NavType.IntType }
            )) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("personajeId") ?: return@composable
                DetailItemScreen(personajeId = id)
            }
            composable(Destination.FavList.route) {
                FavListScreen(
                    onItemClick = { itemId -> navController.navigate(Destination.DetailFav.createRoute(itemId)) }
                )
            }
            composable(Destination.DetailFav.route, arguments = listOf(
                navArgument("personajeId") { type = NavType.IntType }
            )) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("personajeId") ?: return@composable
                DetailFavScreen(personajeId = id)
            }
            composable(Destination.Profile.route) { ProfileScreen() }
            composable(Destination.About.route) { AboutScreen() }
        }
    }
}

@Composable
fun TwoPaneLayout(
    selectedItemId: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val windowSize = getWindowSize()
    // 💡 Requisito: Master-Detail Layout (40% Medium, 30% Expanded)
    val masterWeight = if (windowSize == WindowSize.Expanded) 0.3f else 0.4f

    Row(modifier = modifier.fillMaxSize()) {
        // Panel Master (Lista)
        Box(modifier = Modifier.weight(masterWeight)) {
            ElemListMaster(onItemSelected = onItemSelected) // Usa la versión que solo selecciona
        }

        VerticalDivider()

        // Panel Detail (Detalles)
        Box(modifier = Modifier.weight(1f - masterWeight)) {
            if (selectedItemId != -1) {
                DetailItemScreen(personajeId = selectedItemId)
            } else {
                Text(
                    text = "Selecciona un campeón para ver sus detalles.",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}