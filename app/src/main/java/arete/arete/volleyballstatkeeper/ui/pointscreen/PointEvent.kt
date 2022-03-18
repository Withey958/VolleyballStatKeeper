package arete.arete.volleyballstatkeeper.ui.pointscreen

import arete.arete.volleyballstatkeeper.model.Team

sealed class PointEvent {
    object OnScreenOpened : PointEvent()
    object AddAction: PointEvent()
    data class UpdateWinningTeam(val team: Team): PointEvent()
    object ShowDialog: PointEvent()
    object HideDialog: PointEvent()
}