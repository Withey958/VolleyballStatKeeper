package arete.arete.volleyballstatkeeper.model

import android.graphics.Color
import java.util.*

class Team(
    val name: String,
) {
    var teamPlayers: List<Player>? = null
    var teamColor: Int

    init {
        val rnd = Random()
        teamColor = Color.argb(
            255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)
        )
    }


    fun setPlayers(players: List<Player>) {
        teamPlayers = players
    }
}