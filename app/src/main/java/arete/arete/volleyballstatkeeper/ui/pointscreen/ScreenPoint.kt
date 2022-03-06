package arete.arete.volleyballstatkeeper.ui.pointscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

        val actionList by remember {
            viewModel.pointActionListState
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                Text(text = "Whats happened in this point")
                Text(text = "Score")
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(actionList.size) { action ->
                    ActionItem()
                }
            }
        }
    }
}

@Composable
fun ActionItem() {
    
}