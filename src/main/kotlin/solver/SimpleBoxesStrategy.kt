package solver

import Clue

/**
 * created by imd on 03.07.2022
 */

class SimpleBoxesStrategy : LineStrategy {

    override fun solveLine(currentLine: List<Boolean?>, clues: List<Clue>): List<Boolean?> {
        val n = currentLine.size
        val newLine = MutableList(n) { currentLine[it] }
        val threshold = clues.sumOf { it.cellCount } + clues.size - 1
        if (threshold > currentLine.size)
            throw IllegalStateException("Clues $clues won't fit into line/fragment of length ${currentLine.size}")
        var rSum = 0
        for (clue in clues.map { it.cellCount }) {
            if (clue + threshold > n) {
                val knownCellsCount = clue + threshold - n
                for (i in clue - knownCellsCount until clue)
                    newLine[rSum + i] = true
            }
            rSum += clue + 1
        }
        if (threshold == currentLine.size) {
            var i = 0
            for (clue in clues.dropLast(1)) {
                i += clue.cellCount
                newLine[i++] = false
            }
        }
        return newLine
    }
}