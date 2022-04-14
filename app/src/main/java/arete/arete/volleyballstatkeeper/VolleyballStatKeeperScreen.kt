package arete.arete.volleyballstatkeeper

import androidx.compose.runtime.Composable

enum class VolleyballStatKeeperScreen(
) {
    ActionScreen,
    PointScreen,
    GameScreen,
    HomeScreen,
    CreateTeamScreen;


    companion object {
        fun fromRoute(route: String?): VolleyballStatKeeperScreen =
            when (route?.substringBefore("/")) {
                ActionScreen.name -> ActionScreen
                PointScreen.name -> PointScreen
                GameScreen.name -> GameScreen
                HomeScreen.name -> HomeScreen
                CreateTeamScreen.name -> CreateTeamScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}