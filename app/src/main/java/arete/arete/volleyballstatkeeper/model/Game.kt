package arete.arete.volleyballstatkeeper.model

class Game(
    val homeTeam: Team,
    val awayTeam: Team
) {
    val sets: ArrayList<Set> = arrayListOf()

    fun setSet(set: Set) {
        sets.add(set)
    }

    fun getCurrentSet(): Set {
        return this.sets.last()
    }
}