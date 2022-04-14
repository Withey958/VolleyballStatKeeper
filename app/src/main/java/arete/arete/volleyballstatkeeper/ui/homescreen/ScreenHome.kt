package arete.arete.volleyballstatkeeper.ui.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import arete.arete.volleyballstatkeeper.model.Team
import arete.arete.volleyballstatkeeper.util.UiEvent
import kotlinx.coroutines.flow.collect


@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ScreenHomeViewModel = hiltViewModel()
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TeamSelector()
        }
    }
}

@Preview
@Composable
fun TeamSelector() {
    Column() {
        Text(text = "Team")
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
            TeamItem(team = Team("Norwich"))
            TeamItem(team = Team("Bilbao"))
            TeamItem(team = Team("Ipswich"))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamItem(team: Team) {
    Card(
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color(team.teamColor),
        elevation = 1.dp,
        onClick = {}
    ) {
        Text(text = team.name)
    }
}