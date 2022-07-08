package solver

import Clue

/**
 * created by imd on 08.07.2022
 */

class SimpleSpacesStrategy : LineStrategy {

    override fun solveLine(currentLine: List<Boolean?>, clues: List<Clue>): List<Boolean?> {
        val n = currentLine.size
        val newLine = MutableList(n) { currentLine[it] }

        val isReachable = MutableList(n) { false }
        for (clue in clues) {
            for (i in clue.minPossibleCell..clue.maxPossibleCell)
                isReachable[i] = true
        }

        for (i in 0 until n) {
            if (!isReachable[i])
                newLine[i] = false
        }

        return newLine
    }
}