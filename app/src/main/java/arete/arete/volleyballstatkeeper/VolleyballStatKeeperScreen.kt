package arete.arete.volleyballstatkeeper

import androidx.compose.runtime.Composable

enum class VolleyballStatKeeperScreen(
) {
    ActionScreen,
    PointScreen,
    GameScreen;


    companion object {
        fun fromRoute(route: String?): VolleyballStatKeeperScreen =
            when (route?.substringBefore("/")) {
                ActionScreen.name -> ActionScreen
                PointScreen.name -> PointScreen
                GameScreen.name -> GameScreen
                null -> ActionScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}