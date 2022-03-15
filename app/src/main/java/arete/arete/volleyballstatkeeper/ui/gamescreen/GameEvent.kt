package arete.arete.volleyballstatkeeper.ui.gamescreen

sealed class GameEvent {
    object OnGameStarted: GameEvent()
    object AddPoint: GameEvent()
}