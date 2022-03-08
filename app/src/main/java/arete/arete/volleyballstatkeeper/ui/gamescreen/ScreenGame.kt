package arete.arete.volleyballstatkeeper.ui.gamescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import arete.arete.volleyballstatkeeper.ui.theme.spacing
import arete.arete.volleyballstatkeeper.util.UiEvent

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
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScoreBoard()
    }
}

@Composable
fun ScoreBoard() {
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
                .fillMaxSize(),
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
                    text = "1 - 3",
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
                Text(text = "team 1", color = Color.White)
                Text(text = "Set 1", color = Color.Green)
                Text(text = "team 2", color = Color.White)
            }
        }
    }
}

@Composable
fun PlayerLists(modifier: Modifier, game: Game) {
    Row(modifier = modifier) {
        if (game.homeTeam.teamPlayers != null) {
            LazyColumn {
                items(game.homeTeam.teamPlayers!!) { homePlayer ->
                    PlayerItem(player = homePlayer, modifier = modifier)
                }
            }
        }

        if (game.awayTeam.teamPlayers != null) {
            LazyColumn {
                items(game.awayTeam.teamPlayers!!) { awayPlayer ->
                    PlayerItem(player = awayPlayer, modifier = modifier)
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
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ) {
        Row(modifier = modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(text = "Number", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                Text(text = VolleyballPosition.MIDDLE.toString(), color = Color.DarkGray)
            }
            Text(
                text = player.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

        }
    }

}