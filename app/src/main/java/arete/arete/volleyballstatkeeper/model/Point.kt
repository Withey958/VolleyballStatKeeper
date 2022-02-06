package arete.arete.volleyballstatkeeper.model

class Point(
    val players: List<Player>
) {
    private val actionArrayList = arrayListOf<Action>()
    lateinit var winningTeam: Team
//    val rotation: Int = 1


    fun addAction(newAction: Action) {
        actionArrayList.add(newAction)
    }

    fun getActions(): ArrayList<Action> {
        return actionArrayList
    }

    fun addFinalResult(team: Team) {
        winningTeam = team
    }

}

class Team(
    val name: String,
) {
    private var teamPlayers: List<Player>? = null

    fun setPlayers(players: List<Player>) {
        teamPlayers = players
    }
}
