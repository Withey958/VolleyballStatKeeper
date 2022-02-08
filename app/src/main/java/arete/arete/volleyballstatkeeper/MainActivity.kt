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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.ui.theme.VolleyballStatKeeperTheme

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

@Preview
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
            PointScreenBody()
        }
    )
}

@Composable
fun PointScreenBody() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    ) {
        val players = listOf<Player>(
            Player("Luke"),
            Player("Charlie"),
            Player("Wojtek"),
            Player("Paul"),
            Player("Haris"),
            Player("Nick")
        )
        MultiToggleButtonPlayer(players)
    }
}


@Composable
fun MultiToggleButtonPlayer(
    currentPlayers: List<Player>
) {
    var selectedPlayerIndex: Int? by remember {
        mutableStateOf(null)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Select Player"
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(currentPlayers.size / 2) {
                GenericButton(
                    onClick = { selectedPlayerIndex = it },
                    text = currentPlayers[it].name,
                    selectedPlayerIndex == it
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(currentPlayers.size / 2) {
                GenericButton(
                    onClick = { selectedPlayerIndex = it + currentPlayers.size / 2 },
                    text = currentPlayers[it + currentPlayers.size / 2].name,
                    selected = selectedPlayerIndex == it + currentPlayers.size / 2
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}


@Composable
fun GenericButton(
    onClick: () -> Unit,
    text: String,
    selected: Boolean
) {
    Button(
        modifier = Modifier,
        onClick = onClick,
        shape = RoundedCornerShape(35),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) Color.LightGray else Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}
