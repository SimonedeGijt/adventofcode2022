import java.io.File

class DaySix(
    private val file: File = File(DayOne::class.java.getResource("day6.txt").toURI()),
) {
    private fun getStartOfMessageMarker(window: Int): Int {
        var input = ""
        file.forEachLine { input = it }
        val startOfMessage = input.windowed(window, 1)
            .find { window ->
                window.none { char ->
                    Regex("$char").findAll(window).count() > 1
                }
            }
        println(startOfMessage)
        return input.indexOf(startOfMessage!!).plus(window)
    }

    fun getResultChallangeOne() = getStartOfMessageMarker(4) // thwr
    fun getResultChallangeTwo() = getStartOfMessageMarker(14) // rldchztmwfjvnq
}