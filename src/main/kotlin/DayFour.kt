import java.io.File
import kotlin.math.max
import kotlin.math.min

class DayFour(
    private val file: File = File(DayOne::class.java.getResource("day4.txt").toURI()),
) {
    private fun getPairs(): List<Pair<IntRange, IntRange>> {
        val pairs = mutableListOf<Pair<IntRange, IntRange>>()
        file.forEachLine {
            val list = it.split(delimiters = arrayOf("-", ","))
            pairs.add(Pair(list[0].toInt()..list[1].toInt(), list[2].toInt()..list[3].toInt()))
        }
        return pairs
    }

    private fun isFullyContained(pair: Pair<IntRange, IntRange>) =
        if (pair.first.first > pair.second.first) pair.first.last <= pair.second.last
        else if (pair.first.first == pair.second.first) true
        else pair.second.last <= pair.first.last

    private fun hasOverlap(pair: Pair<IntRange, IntRange>) =
        pair.first.any { it in pair.second }


    fun getResultChallangeOne() = getPairs().count { isFullyContained(it) }
    fun getResultChallangeTwo() = getPairs().count { hasOverlap(it) }
}