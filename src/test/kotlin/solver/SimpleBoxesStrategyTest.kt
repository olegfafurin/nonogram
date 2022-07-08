package solver

import Clue
import org.junit.Test

/**
 * created by imd on 07.07.2022
 */

class SimpleBoxesStrategyTest {

    @Test
    fun solve() {
        val line = MutableList<Boolean?>(10) { null }
        val clue1 = listOf(1,3,4).map { Clue(it) }
        println(SimpleBoxesStrategy().solveLine(line, clue1))
        val clue2 = listOf(3,4).map { Clue(it) }
        println(SimpleBoxesStrategy().solveLine(line, clue2))
        val clue3 = listOf(1,1,1,1,1).map { Clue(it) }
        println(SimpleBoxesStrategy().solveLine(line, clue3))
    }
}