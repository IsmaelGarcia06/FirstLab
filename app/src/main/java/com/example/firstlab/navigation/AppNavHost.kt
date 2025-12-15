package com.example.firstlab.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier // <--- NECESARIA
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstlab.screens.AboutScreen
import com.example.firstlab.screens.DetailFavScreen
import com.example.firstlab.screens.DetailItemScreen
import com.example.firstlab.screens.ElemListScreen
import com.example.firstlab.screens.FavListScreen
import com.example.firstlab.screens.ProfileScreen
import com.example.firstlab.data.getDummyPersonajes
import androidx.compose.foundation.layout.width // Usaremos esta para simular la división si weight falla


// 💡 SIMULACIÓN DE LA CLASE DE TAMAÑO (Para evitar el fallo de getWindowSize)
// Creamos una variable constante y la forzamos a Compact.
// Para probar el Master-Detail, CAMBIA esta variable a true temporalmente.
private const val FORCE_MASTER_DETAIL = false


/**
 * Función principal que gestiona la navegación de la aplicación (NavHost).
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    selectedItemId: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // 💡 1. ELIMINAMOS el código que fallaba (getWindowSize).
    // Usamos la constante para la simulación.
    val isMasterDetail = FORCE_MASTER_DETAIL

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 2. Lógica para manejar el clic en cualquier PersonajeCard
    val handleItemClick: (personajeId: Int, shouldNavigate: Boolean) -> Unit = { id, shouldNavigate ->
        onItemSelected(id)

        if (shouldNavigate && !isMasterDetail) {
            // Navegación en modo Compacto
            navController.navigate("detailitem/$id")
        }
    }

    // 3. Determinar si estamos en una ruta aplicable al diseño Master-Detail
    val isApplicableRoute = currentRoute == Destination.ElemList.route ||
            currentRoute?.startsWith("detailitem/") == true

    if (isMasterDetail && isApplicableRoute) {
        // --- DISEÑO MASTER-DETAIL (SIMPLIFICADO) ---
        MasterDetailLayout(
            selectedItemId = selectedItemId,
            onItemClick = handleItemClick,
            modifier = modifier.fillMaxSize()
        )
    } else {
        // --- DISEÑO COMPACTO (NavHost estándar) ---
        NavHost(
            navController = navController,
            startDestination = Destination.ElemList.route,
            modifier = modifier
        ) {
            // Rutas de la barra inferior
            composable(Destination.ElemList.route) {
                ElemListScreen(onItemClick = handleItemClick)
            }
            composable(Destination.FavList.route) {
                FavListScreen(onItemClick = { /* No hay navegación */ })
            }
            composable(Destination.Profile.route) {
                ProfileScreen()
            }
            composable(Destination.About.route) {
                AboutScreen()
            }

            // Rutas de Detalle simplificadas
            composable(route = "detailitem/{personajeId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("personajeId")?.toIntOrNull() ?: 0
                DetailItemScreen(personajeId = id)
            }
            composable(route = "detailfav/{personajeId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("personajeId")?.toIntOrNull() ?: 0
                DetailFavScreen(personajeId = id)
            }
        }
    }
}

@Composable
private fun MasterDetailLayout(
    selectedItemId: Int,
    onItemClick: (personajeId: Int, shouldNavigate: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultId = remember { getDummyPersonajes().firstOrNull()?.id ?: 1 }
    val currentDetailId = if (selectedItemId != -1) selectedItemId else defaultId

    Row(modifier = modifier) {
        // Panel Maestro (Lista) - DEBEMOS VOLVER A USAR WEIGHT
        ElemListScreen(
            onItemClick = { id, _ -> onItemClick(id, false) },
            modifier = Modifier.weight(0.4f) // <--- USAR WEIGHT OTRA VEZ
        )

        // Panel Detalle
        DetailItemScreen(
            personajeId = currentDetailId,
            modifier = Modifier.weight(0.6f) // <--- USAR WEIGHT OTRA VEZ
        )
    }
}