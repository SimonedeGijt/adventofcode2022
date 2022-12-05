import java.io.File

private const val ROCK_OPPONENT = 'A'
private const val PAPER_OPPONENT = 'B'
private const val SCISSORS_OPPONENT = 'C'

private const val ROCK_YOU = 'X'
private const val PAPER_YOU = 'Y'
private const val SCISSORS_YOU = 'Z'

private const val YOU_SHOULD_LOSE = 'X'
private const val YOU_SHOULD_DRAW = 'Y'
private const val YOU_SHOULD_WIN = 'Z'

private const val ROCK_POINTS = 1
private const val PAPER_POINTS = 2
private const val SCISSORS_POINTS = 3
private const val LOST = 0
private const val DRAW = 3
private const val WON = 6

class DayTwo(
    private val file: File = File(DayOne::class.java.getResource("day2.txt").toURI()),
) {
    fun createMap(): MutableMap<Int, Pair<Char, Char>> {
        val map = mutableMapOf<Int, Pair<Char, Char>>()
        var index = 0
        file.forEachLine {
            map[index] = Pair(it[0], it[2])
            index++
        }
        return map
    }

    fun getCorrectMap(map: MutableMap<Int, Pair<Char, Char>>): Map<Int, Pair<Char, Char>> =
        map.mapValues {
            Pair(
                it.value.first, when (it.value.second) {
                    YOU_SHOULD_LOSE -> getLosingChar(it.value.first)
                    YOU_SHOULD_DRAW -> getDrawChar(it.value.first)
                    YOU_SHOULD_WIN -> getWinningChar(it.value.first)
                    else -> 'X'
                }
            )
        }


    // calculate score of each entry -> write to list
    fun getScore(map: Map<Int, Pair<Char, Char>>): List<Int> =
        map.map {
            when (it.value.second) {
                ROCK_YOU -> playRock(it.value.first) + ROCK_POINTS
                PAPER_YOU -> playPaper(it.value.first) + PAPER_POINTS
                SCISSORS_YOU -> playScissors(it.value.first) + SCISSORS_POINTS
                else -> LOST
            }
        }

    fun getLosingChar(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> SCISSORS_YOU
            PAPER_OPPONENT -> ROCK_YOU
            SCISSORS_OPPONENT -> PAPER_YOU
            else -> 'G'
        }

    fun getDrawChar(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> ROCK_YOU
            PAPER_OPPONENT -> PAPER_YOU
            SCISSORS_OPPONENT -> SCISSORS_YOU
            else -> 'G'
        }

    fun getWinningChar(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> PAPER_YOU
            PAPER_OPPONENT -> SCISSORS_YOU
            SCISSORS_OPPONENT -> ROCK_YOU
            else -> 'G'
        }

    fun playRock(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> DRAW
            PAPER_OPPONENT -> LOST
            SCISSORS_OPPONENT -> WON
            else -> LOST
        }

    fun playPaper(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> WON
            PAPER_OPPONENT -> DRAW
            SCISSORS_OPPONENT -> LOST
            else -> LOST
        }

    fun playScissors(opponent: Char) =
        when (opponent) {
            ROCK_OPPONENT -> LOST
            PAPER_OPPONENT -> WON
            SCISSORS_OPPONENT -> DRAW
            else -> LOST
        }

    fun getResultChallangeOne() = getScore(createMap()).sum()
    fun getResultChallangeTwo() = getScore(getCorrectMap(createMap())).sum()

}
