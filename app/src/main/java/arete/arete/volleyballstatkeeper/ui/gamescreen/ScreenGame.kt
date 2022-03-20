package arete.arete.volleyballstatkeeper.ui.gamescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import arete.arete.volleyballstatkeeper.model.Game
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.model.VolleyballPosition
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
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.Navigate -> onNavigate(event)
                    else -> Unit
                }
            }
        }

        viewModel.onEvent(GameEvent.OnGameStarted)

        val currentGame by remember {
            viewModel.gameState
        }
        val currentTab by remember {
            viewModel.tabbedState
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ScoreBoard(viewModel)
            when (currentTab) {
                1 -> {
                    StatView(viewModel = viewModel)
                    AddPointButton(viewModel = viewModel)
                }
                2 -> {
                    PlayerLists(modifier = Modifier, game = currentGame)
                    AddPointButton(viewModel = viewModel)
                }
                3 -> {

                }
            }
        }
    }
}

@Composable
fun ScoreBoard(viewModel: ScreenGameViewModel) {

    val game by remember {
        viewModel.gameState
    }
    val currentTab by remember {
        viewModel.tabbedState
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val tabButtonModifier = Modifier
                .weight(1f)
                .height(80.dp)
                .border(width = 1.dp, color = Color.Gray)

            TabButton(
                modifier = tabButtonModifier,
                viewModel = viewModel,
                buttonIndex = 1,
                currentTab = currentTab,
                text = "Stats"
            )
            TabButton(
                modifier = tabButtonModifier,
                viewModel = viewModel,
                buttonIndex = 2,
                currentTab = currentTab,
                text = "Players"
            )
            TabButton(
                modifier = tabButtonModifier,
                viewModel = viewModel,
                buttonIndex = 3,
                currentTab = currentTab,
                text = "History",
            )
        }
        Card(
            backgroundColor = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.TopCenter),
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
                        text = "${game!!.sets.last().score[game!!.homeTeam]} - ${game!!.sets.last().score[game!!.awayTeam]}",
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
}

@Composable
fun TabButton(
    modifier: Modifier,
    viewModel: ScreenGameViewModel,
    buttonIndex: Int,
    currentTab: Int,
    text: String
) {
    Button(
        onClick = { viewModel.onEvent(GameEvent.ChangeTab(buttonIndex)) }, modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (buttonIndex == currentTab) Color.Gray else Color.DarkGray,
            contentColor = Color.White
        ),
    ) {
        Text(
            text = text,
            color = if (buttonIndex == currentTab) Color.Green else Color.White,
            modifier = Modifier.align(Alignment.Bottom),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun PlayerLists(modifier: Modifier, game: Game?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .background(color = Color.Gray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(8.dp)
        ) {
            if (game!!.homeTeam.teamPlayers != null) {
                items(game.homeTeam.teamPlayers!!) { homePlayer ->
                    PlayerItem(player = homePlayer, modifier = modifier.height(40.dp))
                }
            }
        }
        Log.d(TAG, "PlayerLists: awayteam = ${game!!.awayTeam.teamPlayers}")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
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
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = player.name, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(
                text = VolleyballPosition.MIDDLE.toString(),
                color = Color.DarkGray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun AddPointButton(viewModel: ScreenGameViewModel) {
    Button(
        onClick = { viewModel.onEvent(GameEvent.AddPoint) },
        modifier = Modifier
            .padding(16.dp)
            .size(width = 160.dp, height = 64.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Circle")
            Text(text = "New Point")
        }
    }
}

@Composable
fun StatView(viewModel: ScreenGameViewModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatItem(statname = "Attacks")
        StatItem(statname = "Blocks")
        StatItem(statname = "Errors")
        StatItem(statname = "Aces")
    }
}

@Composable
fun StatItem(statname: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = statname,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "0", fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(text = "0", fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
    }
}