package day12

fun part1(input: String): String {
    var ans = 0

    for (line in input.lines()) {
        if (": " in line) {
            val (z, n) = line.split(": ")
            val (x, y) = z.split("x").map { it.toInt() }1

            val tiles = listOf(7, 7, 7, 5, 7, 6)
            val nums = n.split(" ").map { it.toInt() }

            val sumProducts = nums.zip(tiles).sumOf { (a, b) -> a * b }

            if (x * y > sumProducts) {
                ans += 1
            }
        }
    }

    return ans.toString()
}

fun part2(input: String): String {
    return "hooray!?"
}
