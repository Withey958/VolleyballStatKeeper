package arete.arete.volleyballstatkeeper.ui.pointscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arete.arete.volleyballstatkeeper.VolleyballStatKeeperScreen
import arete.arete.volleyballstatkeeper.model.Action
import arete.arete.volleyballstatkeeper.model.Game
import arete.arete.volleyballstatkeeper.model.Team
import arete.arete.volleyballstatkeeper.repositories.GameRepository
import arete.arete.volleyballstatkeeper.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ScreenPointViewModel"

@HiltViewModel
class ScreenPointViewModel @Inject constructor(private val repository: GameRepository) :
    ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var setScoreState = mutableStateOf<Map<Team, Int>>(mapOf())
        private set
    var pointActionListState = mutableStateOf<ArrayList<Action>>(arrayListOf())
        private set
    var gameState = mutableStateOf<Game?>(null)
        private set
    var winningTeam = mutableStateOf<Team?>(null)
        private set
    var dialogShowState = mutableStateOf<Boolean>(false)
        private set

    fun onEvent(event: PointEvent) {
        when (event) {
            is PointEvent.OnScreenOpened -> {
                setScoreState.value = repository.getSetScore()
                pointActionListState.value = repository.getPointActions()
                gameState.value = repository.getCurrentGame()
            }
            is PointEvent.AddAction -> {
                sendUiEvent(UiEvent.Navigate(VolleyballStatKeeperScreen.ActionScreen.name))
                Log.d(TAG, "onEvent: navigate pressed")
            }
            is PointEvent.UpdateWinningTeam -> {
                winningTeam.value = event.team
                repository.setPointWinners(event.team)
                sendUiEvent(UiEvent.Navigate(VolleyballStatKeeperScreen.GameScreen.name))
            }
            is PointEvent.ShowDialog -> {
                dialogShowState.value = true
            }
            is PointEvent.HideDialog -> {
                dialogShowState.value = false
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}