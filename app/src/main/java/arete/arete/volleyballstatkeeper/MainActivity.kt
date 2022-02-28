package arete.arete.volleyballstatkeeper

import android.os.Bundle
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
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.ui.actionscreen.ActionScreen
import arete.arete.volleyballstatkeeper.ui.theme.VolleyballStatKeeperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VolleyballStatKeeperTheme() {
                VolleyStatKeeperApp()
            }
        }
    }
}

@Composable
fun VolleyStatKeeperApp() {
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
        },
        content = {
            ActionScreen()
        }
    )
}


