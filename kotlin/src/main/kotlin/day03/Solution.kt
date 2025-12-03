package day03

import java.math.BigInteger

fun part1(input: String): String {
    val banks = input.lines()
    var sum = 0

    banks.forEach {
        var left = 0
        var right = 0
        for ((pos, rating) in it.withIndex()) {
            val ratingInt = rating.digitToInt()
            if (ratingInt > left && pos != it.length-1) {
                left = ratingInt
                right = 0
            }
            else if (ratingInt > right) {
                right = ratingInt
            }
        }
        sum += "$left$right".toInt()
    }

    return sum.toString()
}

fun part2(input: String): String {
    val banks = input.lines()
    var sum = BigInteger("0")

    banks.forEach {
        var number = ""
        var searchSpaceEnd = it.length-12
        var searchSpaceStart = 0
        while (number.length < 12) {
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
