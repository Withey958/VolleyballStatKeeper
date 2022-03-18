package arete.arete.volleyballstatkeeper.repositories

import arete.arete.volleyballstatkeeper.model.*

interface GameRepository {
    fun setTeams(homeTeam: Team, awayTeam: Team)
    fun newSet()
    fun newPoint(point: Point)
    fun addAction(action: Action)
    fun clearGame()
    fun getSetScore(): Map<Team, Int>
    fun getPointActions(): ArrayList<Action>
    fun checkGameStatus(): Boolean
    fun newGame(homeTeam: Team?, awayTeam: Team?)
    fun getCurrentGame(): Game?
    fun setPointWinners(team: Team)
}