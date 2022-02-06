package arete.arete.volleyballstatkeeper.model

import android.icu.text.Transliterator

class Player(val name: String) {
    private val preferredPositions: MutableList<VolleyballPosition> = mutableListOf()

    fun addPreferredPosition(position: VolleyballPosition) {
        preferredPositions.add(position)
    }
}


enum class VolleyballPosition {
    OUTSIDE,
    SETTER,
    MIDDLE,
    OPPOSITE,
    LIBERO
}