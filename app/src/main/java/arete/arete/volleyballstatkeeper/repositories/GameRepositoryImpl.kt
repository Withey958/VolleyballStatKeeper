package arete.arete.volleyballstatkeeper.repositories

import android.util.Log
import arete.arete.volleyballstatkeeper.model.*
import arete.arete.volleyballstatkeeper.model.Set

private const val TAG = "CurrentGame"

class GameRepositoryImpl : GameRepository {
    var game: Game? = null
        private set

    override fun checkGameStatus(): Boolean {
        return game != null
    }

    override fun newGame(homeTeam: Team?, awayTeam: Team?) {

        val homeTeamInput = homeTeam ?: Team("home")
        if (homeTeamInput.teamPlayers.isNullOrEmpty()) {
            homeTeamInput.setPlayers(
                listOf<Player>(
                    Player("Luke"),
                    Player("Charlie"),
                    Player("Wojtek"),
                    Player("Paul"),
                    Player("Haris"),
                    Player("Nick")
                )
            )
        }

        val awayTeamInput = awayTeam ?: Team("away")
        if (awayTeamInput.teamPlayers.isNullOrEmpty()) {
            awayTeamInput.setPlayers(
                listOf<Player>(
                    Player("Luke"),
                    Player("Charlie"),
                    Player("Wojtek"),
                    Player("Paul"),
                    Player("Haris"),
                    Player("Nick")
                )
            )
        }
        game = Game(homeTeamInput, awayTeamInput)
        game!!.setSet(Set(game!!))
    }

    override fun getCurrentGame(): Game? {
        return game
    }

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
        if (game?.sets?.size!! < 2) {
            game?.setSet(newSet)
        } else {
            Log.d(TAG, "newSet: Game Over")
        }
    }

    override fun getSetScore(): Map<Team, Int> {
        if (game?.sets?.size!! == 0) {
            newSet()
        }
        val thisSet = game?.sets?.size!! - 1
        return game!!.sets[thisSet].score
    }

    override fun newPoint(point: Point) {
        val currentSet = game?.sets?.last()
        currentSet?.addPoint(point)
    }

    override fun getPointActions(): ArrayList<Action> {
        val currentSet = game?.sets?.last()
        return currentSet?.points?.last()?.getActions()!!
    }

    override fun addAction(action: Action) {

        val currentSet = game?.sets?.last()
        currentSet?.points?.last()?.addAction(action)
        when (action.actionResult) {
            ActionResult.ACE, ActionResult.BLOCK_KILL, ActionResult.KILL -> {
            }
            ActionResult.ERROR, ActionResult.BLOCKED -> {
            }
            else -> {
            }
        }
    }
}

