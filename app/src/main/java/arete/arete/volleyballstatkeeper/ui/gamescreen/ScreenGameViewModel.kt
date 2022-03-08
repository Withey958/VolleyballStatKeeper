package arete.arete.volleyballstatkeeper.ui.gamescreen

import androidx.lifecycle.ViewModel
import arete.arete.volleyballstatkeeper.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenGameViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {
}