package arete.arete.volleyballstatkeeper

import androidx.compose.runtime.Composable

enum class VolleyballStatKeeperScreen(
    val body: @Composable ((String) -> Unit) -> Unit
) {
    ActionScreen(
        body = { arete.arete.volleyballstatkeeper.ui.actionscreen.ActionScreen() }
    ),
    PointScreen(
        body = {}
    );


    companion object {
        fun fromRoute(route: String?): VolleyballStatKeeperScreen =
            when (route?.substringBefore("/")) {
                ActionScreen.name -> ActionScreen
                null -> ActionScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}