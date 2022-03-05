package arete.arete.volleyballstatkeeper.model

class Set(game: Game) {

    var score: MutableMap<Team, Int> = mutableMapOf(
        Pair(game.homeTeam, 0),
        Pair(game.awayTeam, 0)
    )
    var points: ArrayList<Point> = arrayListOf()

    fun addPoint(point: Point) {
        score[point.winningTeam] = score[point.winningTeam]!! + 1
        points.add(point)
    }
}