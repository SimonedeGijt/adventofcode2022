import java.io.File

class DayThree(
    private val file: File = File(DayThree::class.java.getResource("day3.txt").toURI()),
) {
    private fun getChunkedList(): List<List<String>> {
        val chunked = mutableListOf<List<String>>()
        file.forEachLine { chunked.add(it.chunked(it.length.div(2))) }
        return chunked
    }

    private fun getWrongLetters(chunkedList: List<List<String>>): List<Char> =
        chunkedList.map { chunks ->
            chunks.first().find { chunks.last().contains(it) } ?: 'X'
        }

    private fun getPriority(chars: List<Char>): List<Int> =
        chars.map {
            if (it.isUpperCase()) it.code - 38
            else it.code - 96
        }

    private fun getGroupSublist(): List<Triple<String, String, String>> {
        val list = mutableListOf<String>()
        file.forEachLine { list.add(it) }
        return list.chunked(3).map {
            Triple(it.first(), it[1], it[2])
        }
    }

    private fun getBadgeChars(groups: List<Triple<String, String, String>>): List<Char> =
        groups.map { group ->
            group.first.find { first -> group.second.contains(first).and(group.third.contains(first)) }
                ?: 'X'
        }

    fun getResultChallangeOne() = getPriority(getWrongLetters(getChunkedList())).sum()
    fun getResultChallangeTwo() = getPriority(getBadgeChars(getGroupSublist())).sum()
}