import java.lang.Integer.max
import java.lang.Integer.min

/**
 * created by imd on 07.07.2022
 */

class KnowledgeManager(game: Game, _rowClues: List<List<Int>>, _colClues: List<List<Int>>) {

    val rows: List<MutableList<Clue>> =
        List(game.height) { i -> MutableList(_rowClues[i].size) { j -> Clue(_rowClues[i][j]) } }

    val cols: List<MutableList<Clue>> =
        List(game.width) { i -> MutableList(_colClues[i].size) { j -> Clue(_colClues[i][j]) } }

    init {
        for (i in 0 until game.height) {
            var rs = 0
            for (clue in rows[i]) {
                clue.minPossibleCell = rs
                rs += clue.cellCount + 1
            }
            rs = game.width - 1
            for (clue in rows[i].reversed()) {
                clue.maxPossibleCell = rs
                rs -= clue.cellCount + 1
            }
        }
        for (i in 0 until game.width) {
            var rs = 0
            for (clue in cols[i]) {
                clue.minPossibleCell = rs
                rs += clue.cellCount + 1
            }
            rs = game.height - 1
            for (clue in cols[i].reversed()) {
                clue.maxPossibleCell = rs
                rs -= clue.cellCount + 1
            }
        }
        for (lst in rows)
            println(lst)
        for (lst in cols)
            println(lst)
    }

    private fun maxClueIndex(cellIndex: Int, clues: List<Clue>): Int =
        when (val rb = clues.indexOfFirst { it.minPossibleCell > cellIndex }) {
            -1 -> clues.lastIndex
            else -> rb - 1
        }

    private fun minClueIndex(cellIndex: Int, clues: List<Clue>): Int =
        when (val lb = clues.indexOfLast { it.maxPossibleCell < cellIndex }) {
            clues.lastIndex -> -1
            else -> lb + 1
        }


    private fun updateLineBox(i: Int, clues: MutableList<Clue>) {
        val rb = maxClueIndex(i, clues)
        val lb = minClueIndex(i, clues)
        println("For line with clues $clues and cell index $i: lb = $lb (${clues[lb]}), rb = $rb (${clues[rb]}")

        if (lb == rb) {
            val clue = clues[lb]
            val clueMinBound = clue.minPossibleCell
            val clueMaxBound = clue.maxPossibleCell
            clue.updateBox(i)
            if (clue.minPossibleCell != clueMinBound || clue.maxPossibleCell != clueMaxBound)
                propagate(rb, clues)
        }
    }

    fun updateLineSpace(i: Int, clues: MutableList<Clue>) {
        val rb = maxClueIndex(i, clues)
        val lb = minClueIndex(i, clues)
        for (clueIndex in lb..rb) {
            val clue = clues[clueIndex]
            clue.updateSpace(i)
            propagate(clueIndex, clues)
        }
    }


    private fun propagate(
        clueIndex: Int,
        clues: MutableList<Clue>,
    ) {
        for (j in clueIndex + 1..clues.lastIndex) {
            clues[j].minPossibleCell =
                max(clues[j].minPossibleCell, clues[j - 1].minPossibleCell + clues[j - 1].cellCount + 1)
        }
        for (j in clueIndex - 1..0) {
            clues[j].maxPossibleCell =
                min(clues[j].maxPossibleCell, clues[j + 1].maxPossibleCell - clues[j + 1].cellCount - 1)
        }
    }


    fun updateBox(i: Int, j: Int) {
        updateLineBox(j, rows[i])
        updateLineBox(i, cols[j])
    }

    fun updateSpace(i: Int, j: Int) {
        updateLineSpace(j, rows[i])
        updateLineSpace(i, cols[j])
    }

}