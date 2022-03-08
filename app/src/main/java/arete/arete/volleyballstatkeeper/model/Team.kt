package arete.arete.volleyballstatkeeper.model

class Team(
    val name: String,
) {
    var teamPlayers: List<Player>? = null

    fun setPlayers(players: List<Player>) {
        teamPlayers = players
    }
}