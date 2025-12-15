package com.example.firstlab.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.window.layout.WindowMetricsCalculator
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.xr.compose.testing.toDp

enum class WindowSize { Compact, Medium, Expanded }

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Context.getWindowSize(): WindowSize {
    // Necesitamos obtener la Activity para calcular el WindowSizeClass
    val activity = this.getActivity() ?: return WindowSize.Compact

    // Calcula la clase de tamaño (usa el tamaño actual de la ventana)
    val windowSizeClass = calculateWindowSizeClass(activity = activity)

    return when (windowSizeClass.widthSizeClass) {
        androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Compact -> WindowSize.Compact
        androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Medium -> WindowSize.Medium
        androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Expanded -> WindowSize.Expanded
        else -> WindowSize.Compact
    }
}

// 3. Función auxiliar para encontrar la Activity (necesaria para el cálculo)
fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}