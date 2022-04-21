package arete.arete.volleyballstatkeeper.ui.homescreen

import androidx.compose.foundation.background
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
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            TeamSelector()
            TeamRecord()
            SelectionButtons()
        }
    }
}

@Composable
fun SelectionButtons() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxSize()
            .padding(4.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Team Players")
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(4.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Games")
        }
    }
}

@Composable
fun TeamRecord() {
    // Games played
    // Games won
    // win percentage
    // Kills
    // Blocks
    // Team members
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TeamStat(statname = "Games Played")
        TeamStat(statname = "Games Won")
        TeamStat(statname = "Win Percentage")
        TeamStat(statname = "Kills")
        TeamStat(statname = "Blocks")
        TeamStat(statname = "Team Members")
    }


}

@Composable
fun TeamStat(statname: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = statname,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        Text(text = "0", fontWeight = FontWeight.ExtraBold, color = Color.White)
    }
}

@Composable
fun TeamSelector() {
    Column() {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Team",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
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