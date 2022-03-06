package arete.arete.volleyballstatkeeper.repositories

import arete.arete.volleyballstatkeeper.model.Action
import arete.arete.volleyballstatkeeper.model.Point
import arete.arete.volleyballstatkeeper.model.Team

interface GameRepository {
    fun setTeams(homeTeam: Team, awayTeam: Team)
    fun newSet()
    fun newPoint(point: Point)
    fun addAction(action: Action)
    fun clearGame()
}