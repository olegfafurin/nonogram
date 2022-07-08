import java.lang.Integer.max
import java.lang.Integer.min

/**
 * created by imd on 07.07.2022
 */

class Clue(val cellCount: Int) {
    var minPossibleCell: Int = 0
    var maxPossibleCell: Int = Int.MAX_VALUE
    var minCell: Int? = null
    var maxCell: Int? = null

    override fun toString(): String {
        return "{cnt=$cellCount, l=$minPossibleCell, r=$maxPossibleCell}"
    }

    fun updateBox(i: Int) {
        if (minCell == null || minCell!! > i) {
            minCell = i
        }
        if (maxCell == null || maxCell!! < i) {
            maxCell = i
        }
        relax()
    }

    fun updateSpace(i: Int) {
        if (i - minPossibleCell < cellCount)
            minPossibleCell = i + 1
        else if (maxPossibleCell - i < cellCount)
            maxPossibleCell = i - 1
        relax()
    }

    private fun relax() {
        assert(minPossibleCell <= (minCell ?: Int.MAX_VALUE)) { "Known cell less than lower bound" }
        assert((minCell ?: 0) <= (maxCell ?: 0)) { "Min known cell is greater than the max known cell" }
        assert((maxCell ?: Int.MIN_VALUE) <= maxPossibleCell) { "Known cell greater than upper bound" }

        minPossibleCell = max(minPossibleCell, maxCell!! - cellCount + 1)
        maxPossibleCell = min(maxPossibleCell, minCell!! + cellCount - 1)
        if (maxPossibleCell - minPossibleCell + 1 < 2 * cellCount) {
            minCell = min(minCell!!, maxPossibleCell - cellCount + 1)
            maxCell = max(maxCell!!, minPossibleCell + cellCount - 1)
        }
    }

//    enum class ClueDirection {
//        HORIZONTAL,
//        VERTICAL
//    }
}