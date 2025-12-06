package day06

import java.math.BigInteger
import utils.columns
import utils.matrix
import utils.pad

fun part1(input: String): String {
    var totalSum = BigInteger("0")

    val columns = input.columns("\\s+".toRegex())

    columns.forEach {
        val elems = it.split(" ")
        val operation = elems.last()
        val operands = elems.subList(0, elems.size-1)

        var result = BigInteger(operands.first())
        operands.subList(1, operands.size).forEach { num ->
            if (operation == "+") {
                result += num.toBigInteger()
            }
            else {
                result *= num.toBigInteger()
            }
        }
        totalSum += result
    }

    return totalSum.toString()
}

fun part2(input: String): String {
    var totalSum = BigInteger("0")

    val matrix = input.pad().matrix()
    val operands = mutableListOf<BigInteger>()
    var operator = ' '
    for (col in matrix[0].indices) {
        var allEmpty = true
        var number = ""
        for (line in 0..matrix.size-2) {
            val currChar = matrix[line][col]
            if (currChar.isDigit()) {
                number += currChar
                allEmpty = false
            }
        }
        operator = if (matrix[matrix.size-1][col] != ' ') matrix[matrix.size-1][col] else operator
        if (allEmpty) {
            totalSum += when (operator) {
                '+' -> operands.sumOf { it }
                '*' -> operands.reduce { acc, bigInteger -> acc * bigInteger }
                else -> throw Exception()
            }
            operands.clear()
        }
        else {
            operands.add(number.toBigInteger())
        }
    }

    totalSum += when (operator) {
        '+' -> operands.sumOf { it }
        '*' -> operands.reduce { acc, bigInteger -> acc * bigInteger }
        else -> throw Exception()
    }

    return totalSum.toString()
}
