package arete.arete.volleyballstatkeeper.ui.gamescreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arete.arete.volleyballstatkeeper.VolleyballStatKeeperScreen
import arete.arete.volleyballstatkeeper.model.Game
import arete.arete.volleyballstatkeeper.model.Point
import arete.arete.volleyballstatkeeper.repositories.GameRepository
import arete.arete.volleyballstatkeeper.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenGameViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {
    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var gameState = mutableStateOf<Game?>(null)
        private set

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.OnGameStarted -> {
                if (!repository.checkGameStatus()) {
                    repository.newGame(null, null)
                }
                gameState.value = repository.getCurrentGame()!!
            }
            is GameEvent.AddPoint -> {
                sendUiEvent(UiEvent.Navigate(VolleyballStatKeeperScreen.PointScreen.name))
                repository.newPoint(Point(gameState.value?.homeTeam?.teamPlayers!!))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}