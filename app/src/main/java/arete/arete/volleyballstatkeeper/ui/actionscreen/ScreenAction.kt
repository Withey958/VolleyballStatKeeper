package arete.arete.volleyballstatkeeper.ui.actionscreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player

private const val TAG = "ScreenAction"

@Preview
@Composable
fun ActionScreen() {
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

        val actions = ActionType.getListOfActions()

        Log.d(
            TAG, "ActionScreen: ${
                actions.forEach {
                    it.name
                }
            }"
        )

        Column() {
            MultiToggleButtonPlayer(players)
            MultiToggleButtonAction(actions)
        }
    }
}


@Composable
fun MultiToggleButtonPlayer(
    currentPlayers: List<Player>
) {
    var selectedPlayerIndex: Int? by rememberSaveable {
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
fun MultiToggleButtonAction(
    actions: List<ActionType>
) {
    var selectedActionIndex: Int? by rememberSaveable {
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
            text = "Select Action"
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(actions.size / 2) {
                GenericButton(
                    onClick = { selectedActionIndex = it },
                    text = actions[it].name.substring(range = 0..0)
                            + actions[it].name.substring(startIndex = 1).lowercase(),
                    selectedActionIndex == it
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(actions.size / 2) {
                GenericButton(
                    onClick = { selectedActionIndex = it + actions.size / 2 },
                    text = actions[it + actions.size / 2].name.substring(range = 0..0)
                            + actions[it + actions.size / 2].name.substring(startIndex = 1).lowercase(),
                    selected = selectedActionIndex == it + actions.size / 2
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