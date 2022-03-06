package arete.arete.volleyballstatkeeper

import android.os.Bundle
import android.service.autofill.UserData
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.ui.actionscreen.ActionScreen
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
        VolleyballStateKeeperNavHost(navController = navController)
    }
}

@Composable
fun VolleyballStateKeeperNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = VolleyballStatKeeperScreen.ActionScreen.name,
        modifier = modifier
    ) {
        composable(VolleyballStatKeeperScreen.ActionScreen.name) {
            ActionScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
    }
}


