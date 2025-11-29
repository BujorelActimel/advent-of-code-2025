import java.io.File

fun main(args: Array<String>) {
    // args are validated by the aoc script
    val day = args[0]
    val part = args[1]

    val inputPath = "../inputs/day$day-$part.txt"
    val input = File(inputPath).readText()

    val result = when (day) {
        "01" -> if (part == "1") day01.part1(input) else day01.part2(input)
        "02" -> if (part == "1") day02.part1(input) else day02.part2(input)
        "03" -> if (part == "1") day03.part1(input) else day03.part2(input)
        "04" -> if (part == "1") day04.part1(input) else day04.part2(input)
        "05" -> if (part == "1") day05.part1(input) else day05.part2(input)
        "06" -> if (part == "1") day06.part1(input) else day06.part2(input)
        "07" -> if (part == "1") day07.part1(input) else day07.part2(input)
        "08" -> if (part == "1") day08.part1(input) else day08.part2(input)
        "09" -> if (part == "1") day09.part1(input) else day09.part2(input)
        "10" -> if (part == "1") day10.part1(input) else day10.part2(input)
        "11" -> if (part == "1") day11.part1(input) else day11.part2(input)
        "12" -> if (part == "1") day12.part1(input) else day12.part2(input)
        else -> { // unreachable because script validation, but compiler complains
            println("Unknown day: $day")
            return
        }
    }

    println(result)
}
