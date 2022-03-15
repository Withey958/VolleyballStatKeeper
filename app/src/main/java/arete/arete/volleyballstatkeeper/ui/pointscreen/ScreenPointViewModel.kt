package arete.arete.volleyballstatkeeper.ui.pointscreen

import android.app.UiAutomation
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arete.arete.volleyballstatkeeper.VolleyballStatKeeperScreen
import arete.arete.volleyballstatkeeper.model.Action
import arete.arete.volleyballstatkeeper.model.Team
import arete.arete.volleyballstatkeeper.repositories.GameRepository
import arete.arete.volleyballstatkeeper.ui.actionscreen.ActionEvent
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

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var setScoreState = mutableStateOf<Map<Team, Int>>(mapOf())
        private set
    var pointActionListState = mutableStateOf<ArrayList<Action>>(arrayListOf())
        private set

    fun onEvent(event: PointEvent) {
        when (event) {
            is PointEvent.OnScreenOpened -> {
                setScoreState.value = repository.getSetScore()
                pointActionListState.value = repository.getPointActions()
            }
            is PointEvent.AddAction -> {
                sendUiEvent(UiEvent.Navigate(VolleyballStatKeeperScreen.ActionScreen.name))
                Log.d(TAG, "onEvent: navigate pressed")
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}