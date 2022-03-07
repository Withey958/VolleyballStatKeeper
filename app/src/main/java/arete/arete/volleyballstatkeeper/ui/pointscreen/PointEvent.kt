package arete.arete.volleyballstatkeeper.ui.pointscreen

sealed class PointEvent {
    object OnScreenOpened : PointEvent()
    object AddAction: PointEvent()
}