package solver

import Clue
import Game

/**
 * created by imd on 03.07.2022
 */

interface LineStrategy : Strategy {
    
    override fun solve(game: Game) {
        with(game) {
            for (i in 0 until height)
                modifyRow(i, solveLine(getRow(i), rowClues[i]))
            for (j in 0 until width)
                modifyColumn(j, solveLine(getColumn(j), colClues[j]))
        }
    }

    fun solveLine(currentLine: List<Boolean?>, clues: List<Clue>): List<Boolean?>
}