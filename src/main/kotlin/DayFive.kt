import java.io.File

class DayFive(
    private val file: File = File(DayOne::class.java.getResource("day5.txt").toURI()),
) {
    private fun fileToStringList(): List<String> {
        val stringList = mutableListOf<String>()
        file.forEachLine {
            stringList.add(it)
        }
        return stringList
    }

    private fun getStacks(stringList: List<String>): List<MutableList<Char>> {
        val rows = mutableListOf<List<Char>>()
        var ignore = false
        stringList.map { line ->
            if (line.contains('1')) ignore = true
            if (ignore) return@map

            val chunked = line.chunked(4).map { it.replace(" ", "") }

            val newList = mutableListOf<Char>()
            for (item in 0..8) {
                if (!isValidIndex(chunked, item) || chunked[item].isNullOrBlank()) newList.add(' ')
                else newList.add(chunked[item][1])
            }
            rows.add(newList)
        }

        val stacks = mutableListOf<MutableList<Char>>()
        for (item in 0..8) {
            val newStack = mutableListOf<Char>()
            rows.map { if (it[item] != ' ') newStack.add(it[item]) }
            stacks.add(newStack)
        }
        return stacks
    }

    private fun isValidIndex(array: List<String>, index: Int): Boolean {
        try {
            array[index]
        } catch (e: IndexOutOfBoundsException) {
            return false
        }
        return true
    }

    private fun moveOneByOne(): List<List<Char>> {
        val stringFile = fileToStringList()
        val stacks = getStacks(stringFile)
        val moves = getMoves(stringFile)

        moves.forEach {
            for (item in 1..it.first) {
                stacks[it.third-1].add(0, stacks[it.second-1][0])
                stacks[it.second-1].removeAt(0)
            }
        }

        return stacks
    }

    private fun moveTogether(): List<List<Char>> {
        val stringFile = fileToStringList()
        val stacks = getStacks(stringFile)
        val moves = getMoves(stringFile)

        moves.forEach {
            for (item in 0 until it.first) {
                stacks[it.third-1].add(item, stacks[it.second-1][0])
                stacks[it.second-1].removeAt(0)
            }
        }

        return stacks
    }

    private fun getMoves(stringList: List<String>): MutableList<Triple<Int, Int, Int>> {
        val moves = mutableListOf<Triple<Int, Int, Int>>()
        stringList.filter { it.contains("move") }
            .map { line ->
                val digits = line.split(regex = "[^0-9]".toRegex())
                    .filter { !it.isNullOrBlank() }.map { it.toInt() }
                moves.add(Triple(digits[0], digits[1], digits[2]))
            }
        return moves
    }

    fun getResultChallangeOne() = moveOneByOne().map { it[0] }.onEach { print(it) } // GFTNRBZPF
    fun getResultChallangeTwo() = moveTogether().map { it[0] }.onEach { print(it) } // VRQWPDSGP
}