package arete.arete.volleyballstatkeeper.model

import android.graphics.drawable.Icon
import arete.arete.volleyballstatkeeper.R

/**
 * class with action types and there associated results
 *
 * @param associatedResults potential results of the action
 */
enum class ActionType(val associatedResults: List<ActionResult>, val iconId: Int) {
    ATTACK(
        listOf(
            ActionResult.KILL,
            ActionResult.ERROR,
            ActionResult.BLOCKED,
            ActionResult.NO_RESULT
        ),
        R.drawable.ic_vb_attack_24dp
    ),
    DEFENCE(
        listOf(
            ActionResult.COVERS,
            ActionResult.DIGS,
            ActionResult.ERROR
        ),
        R.drawable.ic_vb_defence_24dp
    ),
    BLOCK(
        listOf(
            ActionResult.BLOCK_KILL,
            ActionResult.ASSIST,
            ActionResult.ERROR,
            ActionResult.NO_RESULT
        ),
        R.drawable.ic_vb_block_24dp
    ),
    SERVICE(
        listOf(
            ActionResult.ACE,
            ActionResult.ERROR,
            ActionResult.OUT_OF_SYSTEM,
            ActionResult.NO_RESULT
        ),
        R.drawable.ic_vb_serve_24dp
    ),
    SET(
        listOf(
            ActionResult.ASSIST,
            ActionResult.ERROR,
            ActionResult.NO_RESULT,
        ),
        R.drawable.ic_vb_set_24dp
    ),
    PASS(
        listOf(
            ActionResult.THREE,
            ActionResult.TWO,
            ActionResult.ONE,
            ActionResult.ZERO
        ),
        R.drawable.ic_vb_pass_24dp
    );


    companion object {
        /**
         * returns all ActionTypes
         */
        fun getListOfActions(): List<ActionType> {
            val actionList = mutableListOf<ActionType>()
            for(action in ActionType.values()) {
                actionList.add(action)
            }
            return actionList.toList()
        }
    }
}