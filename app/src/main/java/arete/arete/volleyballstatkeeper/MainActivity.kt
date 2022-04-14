package arete.arete.volleyballstatkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import arete.arete.volleyballstatkeeper.ui.actionscreen.ActionScreen
import arete.arete.volleyballstatkeeper.ui.gamescreen.GameScreen
import arete.arete.volleyballstatkeeper.ui.homescreen.HomeScreen
import arete.arete.volleyballstatkeeper.ui.pointscreen.PointScreen
import arete.arete.volleyballstatkeeper.ui.theme.VolleyballStatKeeperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VolleyballStatKeeperTheme {
                VolleyStatKeeperApp()
            }
        }
    }
}

@Composable
fun VolleyStatKeeperApp() {
    val allScreens = VolleyballStatKeeperScreen.values().toList()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = VolleyballStatKeeperScreen.fromRoute(backstackEntry.value?.destination?.route)

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Stat Keeper", textAlign = TextAlign.End)
            },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sports_volleyball_24px),
                        contentDescription = "Volleyball Icon"
                    )
                }
            )
        }
    ) {
        VolleyballStatKeeperNavHost(navController = navController)
    }
}

@Composable
fun VolleyballStatKeeperNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = VolleyballStatKeeperScreen.HomeScreen.name,
        modifier = modifier
    ) {
        composable(VolleyballStatKeeperScreen.ActionScreen.name) {
            ActionScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(VolleyballStatKeeperScreen.PointScreen.name) {
            PointScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(VolleyballStatKeeperScreen.GameScreen.name) {
            GameScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(VolleyballStatKeeperScreen.HomeScreen.name) {
            HomeScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
    }
}


