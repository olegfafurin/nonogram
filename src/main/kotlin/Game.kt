import java.lang.StringBuilder

/**
 * created by imd on 03.07.2022
 */

class Game(val width: Int, val height: Int, _rowClues: List<List<Int>>, _colClues: List<List<Int>>) {

    init {
        if (_rowClues.size != height || _colClues.size != width) {
            throw IllegalArgumentException("size of field doesn't correspond to the size of clues")
        }
    }

    val field: MutableList<MutableList<Boolean?>> = MutableList(height) { MutableList(width) { null } }
    val transposedField: MutableList<MutableList<Boolean?>> = MutableList(width) { MutableList(height) { null } }
    private val wizard = KnowledgeManager(this, _rowClues, _colClues)
    private var knownCellsCount = 0

    private val maxRowClues = _rowClues.maxOf { it.size }
    private val maxColClues = _colClues.maxOf { it.size }

    private fun modifyCell(i: Int, j: Int, value: Boolean) {
        if (field[i][j] != null && field[i][j] != value)
            throw IllegalStateException("Attempt to put $value to the cell ($i, $j) already filled with ${field[i][j]}")
        if (field[i][j] == null)
            knownCellsCount += 1
        field[i][j] = value
        transposedField[j][i] = value
        if (value)
            wizard.updateBox(i, j)
        else
            wizard.updateSpace(i, j)
    }

    fun modifyRow(i: Int, newRow: List<Boolean?>) {
        for (j in 0 until width)
            if (newRow[j] != null)
                modifyCell(i, j, newRow[j]!!)
    }

    fun modifyColumn(j: Int, newColumn: List<Boolean?>) {
        for (i in 0 until width)
            if (newColumn[i] != null)
                modifyCell(i, j, newColumn[i]!!)
    }

    fun putBox(i: Int, j: Int) = modifyCell(i, j, true)

    fun putSpace(i: Int, j: Int) = modifyCell(i, j, false)

    fun isComplete(): Boolean = knownCellsCount == width * height

    fun getRow(i: Int): List<Boolean?> = field[i]

    fun getColumn(j: Int): List<Boolean?> = transposedField[j]

    val rowClues: List<List<Clue>>
        get() = wizard.rows

    val colClues: List<List<Clue>>
        get() = wizard.cols

    fun print(): String = StringBuilder().run {
        for (i in 0 until maxColClues) {
            append(" ".repeat(maxRowClues * 2 - 1))
            for (j in 0 until width) {
                val size = colClues[j].size
                if (i + size >= maxColClues) {
                    val clue = colClues[j][i + size - maxColClues]
                    append("${clue.cellCount}".padEnd(2, ' '))
                } else
                    append(" ".repeat(2))
            }
            append('\n')
        }
        for (i in 0 until height) {
            for (t in 0 until maxRowClues) {
                val size = rowClues[i].size
                if (t + size >= maxRowClues) {
                    val clue = rowClues[i][t + size - maxRowClues]
                    append("${clue.cellCount}".padEnd(2, ' '))
                } else
                    append(" ".repeat(2))
            }
            for (j in 0 until width) {
                val value = when (field[i][j]) {
                    true -> "b"
                    false -> "x"
                    null -> " "
                }
                append(value.padEnd(2, ' '))
            }
            append('\n')
        }
        toString()
    }

}