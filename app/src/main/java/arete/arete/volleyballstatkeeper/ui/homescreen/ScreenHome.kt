package arete.arete.volleyballstatkeeper.ui.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            horizontalAlignment = Alignment.Start,
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
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Team",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 26.sp,
        )
        LazyRow(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            item {
                TeamItem(team = Team("Norwich"))
            }
            item {
                TeamItem(team = Team("Bilbao"))
            }
            item {
                TeamItem(team = Team("Ipswich"))

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamItem(team: Team) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(128.dp),
        elevation = 8.dp,
        backgroundColor = Color(team.teamColor),
        onClick = {}
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = team.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}