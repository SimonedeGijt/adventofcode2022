import java.io.File

class DayOne(
    private val file: File = File(DayOne::class.java.getResource("day1.txt").toURI())
) {
    fun getStringFile() =
        DayOne::class.java.getResource("day1.txt").readText()

    // add all values until blank line -> than write it to list
    fun getTotalListOfCals(): List<Int> {
        val listTotalCals = mutableListOf<Int>()
        var tempCountCal = 0
        file.forEachLine {
            if (it.isBlank()) listTotalCals.add(tempCountCal).also { tempCountCal = 0 }
            else tempCountCal += it.toInt()
        }
        return listTotalCals
    }

    fun getResultChallangeOne() = getTotalListOfCals().max()
    fun getResultChallangeTwo() = getTotalListOfCals().sortedDescending().subList(0, 3).sum()
}