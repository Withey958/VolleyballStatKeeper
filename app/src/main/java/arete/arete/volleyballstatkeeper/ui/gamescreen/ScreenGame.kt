package arete.arete.volleyballstatkeeper.ui.gamescreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import arete.arete.volleyballstatkeeper.model.Game
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.model.VolleyballPosition
import arete.arete.volleyballstatkeeper.ui.pointscreen.PointEvent
import arete.arete.volleyballstatkeeper.ui.theme.spacing
import arete.arete.volleyballstatkeeper.util.UiEvent
import kotlinx.coroutines.flow.collect

private const val TAG = "ScreenGame"

@Composable
fun GameScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ScreenGameViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            vertical = MaterialTheme.spacing.extraSmall,
            horizontal = MaterialTheme.spacing.small
        )
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.uiEvent.collect { event ->
                when(event) {
                    is UiEvent.Navigate -> onNavigate(event)
                    else -> Unit
                }
            }
        }

        viewModel.onEvent(GameEvent.OnGameStarted)

        val currentGame by remember {
            viewModel.gameState
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            ScoreBoard(currentGame)
            PlayerLists(modifier = Modifier, game = currentGame)
            AddPointButton(viewModel = viewModel)
        }
    }

}

@Composable
fun ScoreBoard(game: Game?) {
    Card(
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
                Text(
                    text = "${game!!.sets.last().score[game.homeTeam]} - ${game!!.sets.last().score[game.awayTeam]}",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "team ${game!!.homeTeam.name}", color = Color.White)
                Text(text = "Set ${game!!.sets.size}", color = Color.Green)
                Text(text = "team ${game!!.awayTeam.name}", color = Color.White)
            }
        }
    }
}

@Composable
fun PlayerLists(modifier: Modifier, game: Game?) {
    Row(modifier = modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(8.dp)) {
            if (game!!.homeTeam.teamPlayers != null) {
                items(game.homeTeam.teamPlayers!!) { homePlayer ->
                    PlayerItem(player = homePlayer, modifier = modifier.height(40.dp))
                }
            }
        }
        Log.d(TAG, "PlayerLists: awayteam = ${game!!.awayTeam.teamPlayers}")
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            if (game.awayTeam.teamPlayers != null) {
                items(game.awayTeam.teamPlayers!!) { awayPlayer ->
                    PlayerItem(player = awayPlayer, modifier = modifier.height(40.dp))
                }
            }
        }

    }
}

@Composable
fun PlayerItem(
    player: Player,
    modifier: Modifier
) {
    Card(
        backgroundColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = player.name, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = VolleyballPosition.MIDDLE.toString(), color = Color.DarkGray, fontSize = 14.sp)
        }
    }
}

@Composable
fun AddPointButton(viewModel: ScreenGameViewModel) {
    Button(onClick = {viewModel.onEvent(GameEvent.AddPoint)}, modifier = Modifier.padding(16.dp)) {
        Text(text = "add point")
    }
}