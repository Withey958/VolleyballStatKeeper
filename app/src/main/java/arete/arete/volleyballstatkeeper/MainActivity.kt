package arete.arete.volleyballstatkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import arete.arete.volleyballstatkeeper.ui.Component.StatKeeperTabRow
import arete.arete.volleyballstatkeeper.ui.StatKeeperScreen
import arete.arete.volleyballstatkeeper.ui.theme.VolleyballStatKeeperTheme

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

@Preview
@Composable
fun VolleyStatKeeperApp() {
    VolleyballStatKeeperTheme {
        val allScreens = StatKeeperScreen.values().toList()
        var currentScreen by rememberSaveable {
            mutableStateOf(StatKeeperScreen.Overview)
        }
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                StatKeeperTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = StatKeeperScreen.Overview.name,
                modifier = Modifier.padding(innerPadding)
            ) {

            }
        }
    }
}
