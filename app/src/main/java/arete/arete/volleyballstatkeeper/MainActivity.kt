package arete.arete.volleyballstatkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
        Scaffold() {
            
        }
    }
}
