import org.junit.Test

import org.junit.Assert.*

/**
 * created by imd on 07.07.2022
 */

class KnowledgeManagerTest {

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

    val km = KnowledgeManager(game, listOf(
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
        ))

    @Test
    fun getRows() {
    }

    @Test
    fun getCols() {
    }

    @Test
    fun updateLineBox() {
        km.updateBox(2, 4)
    }
}