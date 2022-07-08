package solver

import Game

/**
 * created by imd on 07.07.2022
 */

interface Strategy {
    fun solve(game: Game)
}