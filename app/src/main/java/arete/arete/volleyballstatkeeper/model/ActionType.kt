package arete.arete.volleyballstatkeeper.model

/**
 * class with action types and there associated results
 *
 * @param associatedResult potential results of the action
 */
enum class ActionType(val associatedResult: List<ActionResult>) {
    ATTACK(
        listOf(
            ActionResult.KILL,
            ActionResult.ERROR,
            ActionResult.BLOCKED,
            ActionResult.NO_RESULT
        )
    ),
    DEFENCE(
        listOf(
            ActionResult.COVERS,
            ActionResult.DIGS,
            ActionResult.ERROR
        )
    ),
    BLOCK(
        listOf(
            ActionResult.BLOCK_KILL,
            ActionResult.ASSIST,
            ActionResult.ERROR,
            ActionResult.NO_RESULT
        )
    ),
    SERVICE(
        listOf(
            ActionResult.ACE,
            ActionResult.ERROR,
            ActionResult.OUT_OF_SYSTEM,
            ActionResult.NO_RESULT
        )
    ),
    SET(
        listOf(
            ActionResult.ASSIST,
            ActionResult.ERROR,
            ActionResult.NO_RESULT,
        )
    ),
    PASS(
        listOf(
            ActionResult.THREE,
            ActionResult.TWO,
            ActionResult.ONE,
            ActionResult.ZERO
        )
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