package arete.arete.volleyballstatkeeper.ui.actionscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import arete.arete.volleyballstatkeeper.model.ActionResult
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player
import arete.arete.volleyballstatkeeper.ui.theme.spacing
import kotlinx.coroutines.flow.collect
import arete.arete.volleyballstatkeeper.util.UiEvent
import kotlinx.coroutines.InternalCoroutinesApi

private const val TAG = "ScreenAction"

@Composable
fun ActionScreen(
    viewModel: ScreenActionViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            vertical = MaterialTheme.spacing.extraSmall,
            horizontal = MaterialTheme.spacing.small
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

        val actionResultList by remember {
            viewModel.actionResultList
        }

        val actionSelectedResult by remember {
            viewModel.resultSelectedState
        }

        Column {
            //TODO(lazy way of doing this make multi toggle buttons generic)
            MultiToggleButtonPlayer(players, viewModel)
            MultiToggleButtonAction(actions, viewModel)
            if (actionResultList.isNotEmpty()) {
                MultiToggleButtonResult(actionResultList, viewModel)
            }
            if (actionSelectedResult != null) {
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = { viewModel.onEvent(ActionEvent.OnEnterResult) },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Enter Result")
                }
            }
        }
    }
}


@Composable
fun MultiToggleButtonPlayer(
    currentPlayers: List<Player>,
    viewModel: ScreenActionViewModel
) {
    var selectedPlayerIndex: Int? by rememberSaveable {
        mutableStateOf(null)
    }


    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Select Player"
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(currentPlayers.size / 2) {
                GenericButton(
                    onClick = {
                        selectedPlayerIndex = it
                        viewModel.onEvent(ActionEvent.OnPlayerSelected(currentPlayers[selectedPlayerIndex!!]))
                    },
                    text = currentPlayers[it].name,
                    selectedPlayerIndex == it
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(currentPlayers.size / 2) {
                GenericButton(
                    onClick = {
                        selectedPlayerIndex = it + currentPlayers.size / 2
                        viewModel.onEvent(ActionEvent.OnPlayerSelected(currentPlayers[selectedPlayerIndex!!]))
                    },
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
    actions: List<ActionType>,
    viewModel: ScreenActionViewModel
) {
    var selectedActionIndex: Int? by rememberSaveable {
        mutableStateOf(null)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Select Action"
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(actions.size / 2) {
                GenericButton(
                    onClick = {
                        selectedActionIndex = it
                        viewModel.onEvent(ActionEvent.OnActionTypeSelected(actions[selectedActionIndex!!]))
                    },
                    text = uppercaseStringToCapLowercase(actions[it].name),
                    selectedActionIndex == it
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(actions.size / 2) {
                GenericButton(
                    onClick = {
                        selectedActionIndex = it + actions.size / 2
                        viewModel.onEvent(ActionEvent.OnActionTypeSelected(actions[selectedActionIndex!!]))
                    },
                    text = uppercaseStringToCapLowercase(actions[it + actions.size / 2].name),
                    selected = selectedActionIndex == it + actions.size / 2
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
    }
}

@Composable
fun MultiToggleButtonResult(
    results: List<ActionResult>,
    viewModel: ScreenActionViewModel
) {
    var selectedResultIndex: Int? by rememberSaveable {
        mutableStateOf(null)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Select Result"
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(results.size / 2) {
                GenericButton(
                    onClick = {
                        selectedResultIndex = it
                        viewModel.onEvent(ActionEvent.OnResultTypeSelected(results[selectedResultIndex!!]))
                    },
                    text = uppercaseStringToCapLowercase(results[it].name),
                    selectedResultIndex == it
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(results.size / 2) {
                GenericButton(
                    onClick = {
                        selectedResultIndex = it + results.size / 2
                        viewModel.onEvent(ActionEvent.OnResultTypeSelected(results[selectedResultIndex!!]))
                    },
                    text = uppercaseStringToCapLowercase(results[it + results.size / 2].name),
                    selected = selectedResultIndex == it + results.size / 2
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
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

private fun uppercaseStringToCapLowercase(string: String): String {
    return "${string.substring(range = 0..0)}${string.substring(startIndex = 1).lowercase()}"
}