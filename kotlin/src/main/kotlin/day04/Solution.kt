package day04

fun part1(input: String): String {
    var answer = 0
    val lines = input.lines()
    for ((i, line) in lines.withIndex()) {
        for ((j, spot) in line.withIndex()) {
            if (spot == '@' && countAdjRolls(lines, i, j) < 4) {
                answer++
            }
        }
    }
    return answer.toString()
}

fun part2(input: String): String {
    var answer = 0
    var lines = input.lines().toMutableList()
    do {
        var changed = false
        for ((i, line) in lines.withIndex()) {
            var newLine = ""
            for ((j, spot) in line.withIndex()) {
                if (spot == '@' && countAdjRolls(lines, i, j) < 4) {
                    answer++
                    newLine += '.'
                    changed=true
                } else {
                    newLine += spot
                }
            }
            lines[i] = newLine
        }
    } while(changed)
    return answer.toString()
}

fun countAdjRolls(lines: List<String>, line: Int, col: Int): Int {
    val directions = listOf(0, -1, 1)
    var count = 0
    for (x in directions) {
        for (y in directions) {
            if (x != 0 || y != 0) {
                if (
                    line+x >= 0 && 
                    line+x < lines.size &&
                    col+y >= 0 &&
                    col+y < lines[0].length &&
                    lines[line+x][col+y] == '@'
                ) {
                    count++
                }
            }
        }
    }
    return count
}
