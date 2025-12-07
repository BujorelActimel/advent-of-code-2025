package day07

import utils.matrix
import java.math.BigInteger

fun part1(input: String): String {
    val map = input.matrix()
    val startPosition = Pos(0,0)
    for ((i, line) in map.withIndex()) {
        for ((j,el) in line.withIndex()) {
            if (el == 'S') {
                startPosition.x = i
                startPosition.y = j
            }
        }
    }

    val size = map.size
    var result = 0
    for (i in 2..<size step 2) {
        val interval = (size-i)/2..<(size-i)/2+(i-1)
        for (j in interval step 2) {
            if (map[i][j] == '^' && hasBeam(map, i, j)) {
                result++
            }
        }
    }
    return result.toString()
}

fun hasBeam(map: List<List<Char>>, line: Int, column: Int): Boolean {
    var currLine = line - 2
    while (currLine >= 0) {
        if (column - 1 >= 0 && map[currLine][column-1] == '^') {
            return true
        }
        if (column + 1 < map.size && map[currLine][column+1] == '^') {
            return true
        }
        if (map[currLine][column] == 'S') {
            return true
        }
        if (map[currLine][column] != '.') {
            return false
        }
        currLine -= 2
    }
    return false
}

fun part2(input: String): String {
    val map = input.matrix()
    val startPosition = Pos(0,0)
    for ((i, line) in map.withIndex()) {
        for ((j,el) in line.withIndex()) {
            if (el == 'S') {
                startPosition.x = i
                startPosition.y = j
            }
        }
    }

    val cache = HashMap<Pos, BigInteger>()

    return (BigInteger.ONE+countPaths(map, startPosition, cache)).toString()
}

fun countPaths(map: List<List<Char>>, pos: Pos, cache: HashMap<Pos, BigInteger>): BigInteger {
    if (!validPos(map, pos)) {
        return BigInteger.ZERO
    }

    if (map[pos.x][pos.y] == '^') {
        val left = Pos(pos.x, pos.y-1)
        val right = Pos(pos.x, pos.y+1)

        if (!cache.containsKey(left)) {
            cache[left] = countPaths(map, left, cache)
        }
        val leftPaths = cache[left]!!

        if (!cache.containsKey(right)) {
            cache[right] = countPaths(map, right, cache)
        }
        val rightPaths = cache[right]!!

        return BigInteger.ONE + leftPaths + rightPaths
    }
    val down = Pos(pos.x+1, pos.y)

    return countPaths(map, down, cache)
}

fun validPos(map: List<List<Char>>, pos: Pos): Boolean {
    return pos.x < map.size &&
           pos.x >= 0 &&
           pos.y >= 0 &&
           pos.y < map.size
}

data class Pos(
    var x: Int,
    var y: Int,
)
