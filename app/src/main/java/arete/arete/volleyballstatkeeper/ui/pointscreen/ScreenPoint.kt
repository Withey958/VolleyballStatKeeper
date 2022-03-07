package arete.arete.volleyballstatkeeper.ui.pointscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import arete.arete.volleyballstatkeeper.model.Action
import arete.arete.volleyballstatkeeper.model.ActionResult
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.ui.theme.spacing

@Preview
@Composable
fun PointScreen(
    viewModel: ScreenPointViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            vertical = MaterialTheme.spacing.extraSmall,
            horizontal = MaterialTheme.spacing.small
        )
    ) {
        viewModel.onEvent(PointEvent.OnScreenOpened)
        val currentSetScore by remember {
            viewModel.setScoreState
        }
        val scaffoldState = rememberScaffoldState()
//        val actionList by remember {
//            viewModel.pointActionListState
//        }
        val actionList = arrayListOf<Action>(
            Action(Player("Keith"), ActionType.ATTACK, ActionResult.BLOCKED)
        )

        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //TODO add on click
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        ) {
            Column(modifier = Modifier) {
                Row() {
                    Text(text = "Whats happened in this point")
                    Text(text = "Score")
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(actionList) { action ->
                        ActionItem(action, actionList.indexOf(action) + 1)
                    }
                }
            }
        }
    }
}

@Composable
fun ActionItem(
    action: Action,
    indexNo: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Icon(
                    painter = painterResource(id = action.actionType.iconId),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .size(64.dp)
                )

            }
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = action.player.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Action: ${action.actionType}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    text = "Result: ${action.actionResult.toString()}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Action Number",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxHeight()
                )
                Text(
                    text = indexNo.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxHeight()
                )

            }
        }
    }
}
