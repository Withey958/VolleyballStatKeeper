package arete.arete.volleyballstatkeeper.ui.actionscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arete.arete.volleyballstatkeeper.VolleyballStatKeeperScreen
import arete.arete.volleyballstatkeeper.model.*
import arete.arete.volleyballstatkeeper.repositories.GameRepository
import arete.arete.volleyballstatkeeper.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ScreenActionViewModel"

@HiltViewModel
class ScreenActionViewModel @Inject constructor(private val repository: GameRepository) :
    ViewModel() {

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var playerSelectedState = mutableStateOf<Player?>(null)
        private set
    var actionSelectedState = mutableStateOf<ActionType?>(null)
        private set
    var resultSelectedState = mutableStateOf<ActionResult?>(null)
        private set
    var actionResultList = mutableStateOf<List<ActionResult>>(listOf())
        private set


    fun onEvent(event: ActionEvent) {
        when (event) {
            is ActionEvent.OnPlayerSelected -> {
                playerSelectedState.value = event.player
                Log.d(TAG, "onEvent: selected player ${event.player.name}")
            }
            is ActionEvent.OnActionTypeSelected -> {
                actionSelectedState.value = event.actionType
                Log.d(TAG, "onEvent: selected action ${event.actionType}")
                actionResultList.value = event.actionType.associatedResults
            }
            is ActionEvent.OnResultTypeSelected -> {
                resultSelectedState.value = event.resultType
                Log.d(TAG, "onEvent: selected action ${event.resultType}")
            }
            is ActionEvent.OnEnterResult -> {
                repository.addAction(
                    Action(
                        player = playerSelectedState.value!!,
                        actionType = actionSelectedState.value!!,
                        actionResult = resultSelectedState.value!!
                    )
                )
                Log.d(TAG, "onEvent: enter result")
                sendUiEvent(UiEvent.Navigate(VolleyballStatKeeperScreen.PointScreen.name))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}