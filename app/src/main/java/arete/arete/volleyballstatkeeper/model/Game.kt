package arete.arete.volleyballstatkeeper.model

class Game(
    val homeTeam: Team,
    val awayTeam: Team
) {
    val sets: ArrayList<Set> = arrayListOf()

    fun setSet(set: Set, setNo: Int) {
        sets.add(setNo, set)
    }
}