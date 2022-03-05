package arete.arete.volleyballstatkeeper.ui.actionscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import arete.arete.volleyballstatkeeper.model.ActionResult
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "ScreenActionViewModel"
@HiltViewModel
class ScreenActionViewModel @Inject constructor(): ViewModel() {

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
        }
    }


}