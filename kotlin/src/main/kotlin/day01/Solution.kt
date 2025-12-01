package day01

import day01.rotate

fun part1(input: String): String {
    var password = 0
    var dialPosition = 50
    val rotations = input.lines()

    rotations.forEach {
        dialPosition = rotate(dialPosition, it)
        if (dialPosition == 0) {
            password++
        }
    }

    return password.toString()
}

fun part2(input: String): String {
    // TODO: Implement solution for day 01 part 2
    return "Not implemented"
}

fun rotate(dialPosition: Int, rotation: String): Int {
    if (rotation.isEmpty() || rotation.isBlank()) {
        return dialPosition
    }

    val direction = rotation[0]
    val distance = rotation.substring(1).toInt()

    if (direction == 'L') {
        if (dialPosition - distance >= 0) {
            return dialPosition - distance
        }
        else {
            return -((distance - dialPosition) % 100)
        }
    }
    else {
        if (dialPosition + distance < 100) {
            return dialPosition + distance
        }
        else {
            return (dialPosition + distance) % 100
        }
    }
}
