package arete.arete.volleyballstatkeeper.ui.actionscreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import arete.arete.volleyballstatkeeper.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenActionViewModel: ViewModel() {

    private val _playerSelectedState: MutableStateFlow<Player?> = MutableStateFlow(null)
    val playerStateFlow = _playerSelectedState.asStateFlow()

    fun selectPlayerForAction(currentPlayer: Player) {
        _playerSelectedState.value = currentPlayer
    }
}