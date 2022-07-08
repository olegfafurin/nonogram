import org.junit.Test

import org.junit.Assert.*

/**
 * created by imd on 03.07.2022
 */

class GameTest {

    val game = Game(5, 5, listOf(
        listOf(1, 1),
        listOf(2, 1),
        listOf(3),
        listOf(),
        listOf(1, 1, 1)
    ),
        listOf(
            listOf(4),
            listOf(3, 1),
            listOf(2, 2),
            listOf(1, 1),
            listOf(1, 1, 1)
        )
    )

    @Test
    fun print() {
        print(game.print())
        game.putBox(0, 0)
        print(game.print())
        game.putSpace(1, 4)
        print(game.print())
    }
}