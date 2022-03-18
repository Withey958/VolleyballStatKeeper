package arete.arete.volleyballstatkeeper.model

class Set(game: Game) {

    var score: MutableMap<Team, Int> = mutableMapOf(
        Pair(game.homeTeam, 0),
        Pair(game.awayTeam, 0)
    )
    var points: ArrayList<Point> = arrayListOf()

    fun newPoint(point: Point) {
        points.add(point)
    }

    fun updateScore() {
        score[points.last().winningTeam] = score[points.last().winningTeam]!! + 1
    }
}