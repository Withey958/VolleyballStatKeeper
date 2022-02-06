package arete.arete.volleyballstatkeeper.model

class Set(game: Game) {
    var score: Pair<Int, Int> = Pair(0,0)
    var points: ArrayList<Point> = arrayListOf()
}

class Game(
    val homeTeam: Team,
    val awayTeam: Team
) {
    val sets: ArrayList<Set> = arrayListOf()

    fun setSet(set: Set, setNo: Int) {
        sets.add(setNo, set)
    }
}