package arete.arete.volleyballstatkeeper.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    /** 0dp */
    val default: Dp = 0.dp,
    /** 4dp */
    val extraSmall: Dp = 4.dp,
    /** 8dp */
    val small: Dp = 8.dp,
    /** 16dp */
    val medium: Dp = 16.dp,
    /** 32dp */
    val large: Dp = 32.dp,
    /** 64dp */
    val extraLarge: Dp = 64.dp
)


val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current