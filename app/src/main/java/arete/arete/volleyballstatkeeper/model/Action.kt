package arete.arete.volleyballstatkeeper.model

import android.util.Log

private const val TAG = "Action"

class Action(
    val player: Player,
    val actionType: ActionType,
    var actionResult: ActionResult
) {

    fun getActionResultList(): List<ActionResult> {
        return actionType.associatedResults
    }

    fun setResult(result: ActionResult) {
        if (getActionResultList().contains(result)) {
            actionResult = result
        } else {
            Log.d(TAG, "setResult: action does not have associated result")
        }
    }
}
