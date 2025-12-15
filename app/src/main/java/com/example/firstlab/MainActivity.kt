package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.firstlab.navigation.AppNavHost
import com.example.firstlab.ui.theme.FirstLabTheme
import androidx.compose.material3.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.firstlab.navigation.BottomNavItem
import com.example.firstlab.navigation.Destination

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FirstLabTheme {
                MainAppContainer()
            }
        }
    }
}

@Composable
fun MainAppContainer() {
    val navController = rememberNavController()
    var selectedItemId by rememberSaveable { mutableIntStateOf(-1) }

    // Obtenemos la ruta actual para saber si mostrar la barra o no
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Definimos qué rutas principales deben mostrar la barra inferior
    val showBottomBar = currentRoute in listOf(
        Destination.ElemList.route,
        Destination.FavList.route,
        Destination.Profile.route,
        Destination.About.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // 💡 IMPLEMENTACIÓN DE LA BARRA INFERIOR
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface // Usa el color oscuro de las tarjetas
                ) {
                    val items = listOf(BottomNavItem.Personajes, BottomNavItem.Favoritos, BottomNavItem.Perfil, BottomNavItem.AcercaDe)

                    items.forEach { item ->
                        val isSelected = currentRoute == item.route
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        // Evita construir una pila de pestañas
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            selectedItemId = selectedItemId,
            onItemSelected = { selectedItemId = it },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

