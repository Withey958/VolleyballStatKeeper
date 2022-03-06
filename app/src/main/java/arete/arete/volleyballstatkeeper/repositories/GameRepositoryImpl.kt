package arete.arete.volleyballstatkeeper.repositories

import android.util.Log
import arete.arete.volleyballstatkeeper.model.*
import arete.arete.volleyballstatkeeper.model.Set

private const val TAG = "CurrentGame"

class GameRepositoryImpl: GameRepository {
    var game: Game? = null
        private set

    override fun clearGame() {
        game = null
    }

    override fun setTeams(homeTeam: Team, awayTeam: Team) {
        game = Game(homeTeam, awayTeam)
    }

    override fun newSet() {
        if (game == null) {
            Log.d(TAG, "newSet: game is not init please set Teams")
            return
        }

        val newSet = Set(game!!)
        if(game?.sets?.size!! > 2) {
            game?.setSet(newSet, game?.sets?.size!!)
        } else {
            Log.d(TAG, "newSet: Game Over")
        }
    }

    override fun newPoint(point: Point) {
        val currentSet = game?.sets?.last()
        currentSet?.addPoint(point)
    }

    override fun addAction(action: Action) {
        val currentSet = game?.sets?.last()
        currentSet?.points?.last()?.addAction(action)
        when(action.actionResult) {
            ActionResult.ACE, ActionResult.BLOCK_KILL, ActionResult.KILL -> { }
            ActionResult.ERROR, ActionResult.BLOCKED -> { }
            else -> { }
        }
    }
}