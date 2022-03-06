package arete.arete.volleyballstatkeeper.ui.actionscreen

import arete.arete.volleyballstatkeeper.model.Action
import arete.arete.volleyballstatkeeper.model.ActionResult
import arete.arete.volleyballstatkeeper.model.ActionType
import arete.arete.volleyballstatkeeper.model.Player

sealed class ActionEvent {
    data class OnPlayerSelected(val player: Player): ActionEvent()
    data class OnActionTypeSelected(val actionType: ActionType): ActionEvent()
    data class OnResultTypeSelected(val resultType: ActionResult): ActionEvent()
    object OnEnterResult: ActionEvent()
}
