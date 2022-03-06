package arete.arete.volleyballstatkeeper

import androidx.compose.runtime.Composable

enum class VolleyballStatKeeperScreen(
) {
    ActionScreen,
    PointScreen;


    companion object {
        fun fromRoute(route: String?): VolleyballStatKeeperScreen =
            when (route?.substringBefore("/")) {
                ActionScreen.name -> ActionScreen
                PointScreen.name -> PointScreen
                null -> ActionScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}