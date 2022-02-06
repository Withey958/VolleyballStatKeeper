package arete.arete.volleyballstatkeeper.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
/**
 * Screen metadata for Rally.
 */
enum class StatKeeperScreen(
    val icon: ImageVector,
    val body: @Composable ((String) -> Unit) -> Unit
) {
    Overview(
        icon = Icons.Default.ArrowForward,
        body = { StartBody() }
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): StatKeeperScreen =
            when (route?.substringBefore("/")) {
                Overview.name -> Overview
                null -> Overview
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
