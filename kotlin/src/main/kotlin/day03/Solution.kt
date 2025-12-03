package day03

import java.math.BigInteger

fun part1(input: String): String {
    val resultLength = 2
    val banks = input.lines()
    var sum = BigInteger("0")

    banks.forEach {
        var number = ""
        var searchSpaceEnd = it.length-resultLength
        var searchSpaceStart = 0
        while (number.length < resultLength) {
            val (digit, index) = it.getMax(searchSpaceStart, searchSpaceEnd)
            number += digit.toString()
            searchSpaceStart = index+1
            searchSpaceEnd++
        }
        sum += number.toBigInteger()    
    }

    return sum.toString()
}

fun part2(input: String): String {
    val resultLength = 12
    val banks = input.lines()
    var sum = BigInteger("0")

    banks.forEach {
        var number = ""
        var searchSpaceEnd = it.length-resultLength
        var searchSpaceStart = 0
        while (number.length < resultLength) {
            val (digit, index) = it.getMax(searchSpaceStart, searchSpaceEnd)
            number += digit.toString()
            searchSpaceStart = index+1
            searchSpaceEnd++
        }
        sum += number.toBigInteger()    
    }

    return sum.toString()
}

fun String.getMax(startIndex: Int, endIndex: Int): Pair<Int, Int> {
    var max = this[startIndex].digitToInt()
    var maxIndex = startIndex
    for (i in startIndex+1..endIndex) {
        val elem = this[i].digitToInt()
        if (elem > max) {
            max = elem
            maxIndex = i
        }
    }
    return Pair(max, maxIndex)
}
