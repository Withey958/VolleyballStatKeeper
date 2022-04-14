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

    private fun updateScore() {
        score[points.last().winningTeam] = score[points.last().winningTeam]!! + 1
    }

    fun getPoint(pointIndex: Int = -1): Point {
        return if (pointIndex == -1) {
            this.points.last()
        } else {
            this.points[pointIndex]
        }
    }

    fun getPointActions(pointIndex: Int ): ArrayList<Action> {
        return if (pointIndex == -1) {
            this.points.last().getActions()
        } else {
            this.points[pointIndex].getActions()
        }
    }

    fun setPointWinners(team: Team) {
        this.points.last().addFinalResult(team)
        this.updateScore()
    }

}