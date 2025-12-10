package day10

import com.google.ortools.Loader
import com.google.ortools.linearsolver.MPSolver

fun part1(input: String): String {
    var totalPresses = 0

    for (line in input.lines()) {
        if (line.isBlank()) continue

        val parts = line.split(" ")
        val targetLights = Lights(parts[0])
        val buttons = parts.subList(1, parts.size - 1).map {
            it.drop(1).dropLast(1).split(",").map { x -> x.toInt() }
        }

        val (_, presses) = allButtonSequences(
            Lights(MutableList(targetLights.lightsState.size) { false }),
            targetLights,
            buttons,
            0,
            mutableListOf()
        )
        totalPresses += presses.size
    }

    return totalPresses.toString()
}

fun part2(input: String): String {
    Loader.loadNativeLibraries()

    var totalPresses = 0L
    val lines = input.lines().filter { it.isNotBlank() }
    val totalLines = lines.size

    lines.forEachIndexed { index, line ->
        val parts = line.split(" ")
        val buttons = parts.subList(1, parts.size - 1).map {
            it.drop(1).dropLast(1).split(",").map { x -> x.toInt() }
        }
        val target = parts.last().drop(1).dropLast(1).split(",").map { it.toInt() }

        val presses = solveWithORTools(target, buttons)

        if (presses == Long.MAX_VALUE) {
            return "ERROR at ${index + 1}"
        }

        totalPresses += presses
    }

    return totalPresses.toString()
}

fun solveWithORTools(target: List<Int>, buttons: List<List<Int>>): Long {
    val solver = MPSolver.createSolver("SCIP")

    val infinity = Double.POSITIVE_INFINITY

    val vars = buttons.indices.map { i ->
        solver.makeIntVar(0.0, infinity, "button_$i")
    }

    for (counterIdx in target.indices) {
        val constraint = solver.makeConstraint(
            target[counterIdx].toDouble(),
            target[counterIdx].toDouble(),
            "counter_$counterIdx"
        )

        buttons.forEachIndexed { buttonIdx, button ->
            if (counterIdx in button) {
                constraint.setCoefficient(vars[buttonIdx], 1.0)
            }
        }
    }

    val objective = solver.objective()
    vars.forEach { v ->
        objective.setCoefficient(v, 1.0)
    }
    objective.setMinimization()

    return when (val resultStatus = solver.solve()) {
        MPSolver.ResultStatus.OPTIMAL -> {
            vars.sumOf { it.solutionValue().toLong() }
        }
        MPSolver.ResultStatus.FEASIBLE -> {
            vars.sumOf { it.solutionValue().toLong() }
        }
        else -> {
            println("No solution: $resultStatus")
            Long.MAX_VALUE
        }
    }
}

data class Lights(val lightsState: MutableList<Boolean>) {
    constructor(other: Lights) : this(other.lightsState.toMutableList())
    constructor(config: String) : this(
        config.mapNotNull { ch ->
            when (ch) {
                '.' -> false
                '#' -> true
                else -> null
            }
        }.toMutableList()
    )

    fun pushButton(button: List<Int>): Lights {
        val newLights = Lights(this)
        for (i in button) newLights.lightsState[i] = !newLights.lightsState[i]
        return newLights
    }

    override fun equals(other: Any?): Boolean {
        return other is Lights && lightsState == other.lightsState
    }

    override fun hashCode(): Int = lightsState.hashCode()
}

fun allButtonSequences(
    curr: Lights,
    target: Lights,
    buttons: List<List<Int>>,
    step: Int,
    pressed: MutableList<List<Int>>
): Pair<Boolean, MutableList<List<Int>>> {
    if (curr == target) return true to pressed.toMutableList()
    if (step >= buttons.size) return false to mutableListOf()

    // skip button
    val option1 = allButtonSequences(curr, target, buttons, step + 1, pressed.toMutableList())

    // press button
    val newLights = curr.pushButton(buttons[step])
    val pressedCopy = pressed.toMutableList()
    pressedCopy.add(buttons[step])
    val option2 = allButtonSequences(newLights, target, buttons, step + 1, pressedCopy)

    return when {
        option1.first && option2.first -> if (option2.second.size < option1.second.size) option2 else option1
        option1.first -> option1
        option2.first -> option2
        else -> false to mutableListOf()
    }
}
