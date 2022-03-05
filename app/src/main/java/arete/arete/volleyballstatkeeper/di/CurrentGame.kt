package arete.arete.volleyballstatkeeper.di

import android.util.Log
import arete.arete.volleyballstatkeeper.model.*
import arete.arete.volleyballstatkeeper.model.Set

private const val TAG = "CurrentGame"

object CurrentGame {
    var game: Game? = null
        private set



    fun setTeams(homeTeam: Team, awayTeam: Team) {
        game = Game(homeTeam, awayTeam)
    }

    fun newSet() {
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

    fun newPoint(point: Point) {
        val currentSet = game?.sets?.last()
        currentSet?.addPoint(point)
    }

    fun addAction(action: Action) {
        val currentSet = game?.sets?.last()
        currentSet?.points?.last()?.addAction(action)
        when(action.actionResult) {
            ActionResult.ACE, ActionResult.BLOCK_KILL, ActionResult.KILL -> { }
        }

    }
}