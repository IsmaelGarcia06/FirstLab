package com.example.firstlab.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.window.layout.WindowMetricsCalculator
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.xr.compose.testing.toDp

enum class WindowSize { Compact, Medium, Expanded }

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun getWindowSize(): WindowSize {
    val activity = LocalContext.current as Activity
    val windowMetrics = remember(activity) { WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity) }

    // Calcula el tamaño en Dp
    val dpSize = DpSize(windowMetrics.bounds.width().toDp(), windowMetrics.bounds.height().toDp())

    val windowSizeClass = WindowSizeClass.calculateFromSize(dpSize)

    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> WindowSize.Compact
        WindowWidthSizeClass.Medium -> WindowSize.Medium
        WindowWidthSizeClass.Expanded -> WindowSize.Expanded
        else -> WindowSize.Compact
    }
}