package arete.arete.volleyballstatkeeper.ui.actionscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import arete.arete.volleyballstatkeeper.model.ActionResult
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val TAG = "ScreenActionViewModel"
@HiltViewModel
class ScreenActionViewModel @Inject constructor(): ViewModel() {

    private val _playerSelectedState: MutableStateFlow<Player?> = MutableStateFlow(null)
    val playerStateFlow = _playerSelectedState.asStateFlow()

    private val _actionSelectedState: MutableStateFlow<ActionType?> = MutableStateFlow(null)
    val actionStateFlow = _playerSelectedState.asStateFlow()

    private val _actionSelectedResult: MutableStateFlow<ActionResult?> = MutableStateFlow(null)
    val actionSelectedResult = _playerSelectedState.asStateFlow()

    var actionResultList = mutableStateOf<List<ActionResult>>(listOf())



    fun onEvent(event: ActionEvent) {
        when (event) {
            is ActionEvent.OnPlayerSelected -> {
                _playerSelectedState.value = event.player
                Log.d(TAG, "onEvent: selected player ${event.player.name}")
            }
            is ActionEvent.OnActionTypeSelected -> {
                _actionSelectedState.value = event.actionType
                Log.d(TAG, "onEvent: selected action ${event.actionType}")
                actionResultList.value = event.actionType.associatedResults
            }
            is ActionEvent.OnResultTypeSelected -> {
                _actionSelectedResult.value = event.resultType
                Log.d(TAG, "onEvent: selected action ${event.resultType}")
            }
        }
    }


}