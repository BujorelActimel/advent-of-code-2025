package day01

fun part1(input: String): String {
    var password = 0
    var dialPosition = 50
    val rotations = input.lines()

    rotations.forEach {
        if (!it.isEmpty() && !it.isBlank()) {
            val direction = it[0]
            val distance = it.substring(1).toInt() % 100

            when (direction) {
                'L' -> {
                    dialPosition -= distance
                }
                'R' -> {
                    dialPosition += distance
                }
            }

            dialPosition = dialPosition.mod(100)
        }

        if (dialPosition == 0) {
            password++
        }
    }

    return password.toString()
}

fun part2(input: String): String {
    var password = 0
    var dialPosition = 50
    val rotations = input.lines()

    rotations.forEach {
        if (!it.isEmpty() && !it.isBlank()) {
            val direction = it[0]
            var distance = it.substring(1).toInt()

            password += (distance / 100)
            distance = distance.mod(100)

            when (direction) {
                'L' -> {
                    if (dialPosition - distance <= 0 && dialPosition != 0) {
                        password++
                    }
                    dialPosition = (dialPosition - distance).mod(100)
                }
                'R' -> {
                    if (dialPosition + distance >= 100 && dialPosition != 0) {
                        password++
                    }
                    dialPosition = (dialPosition + distance).mod(100)
                }
            }
        }
    }
    
    return password.toString()
}
