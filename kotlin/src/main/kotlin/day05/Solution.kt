package day05

import java.math.BigInteger
import utils.*

fun part1(input: String): String {
    val lines = input.lines()
    val ranges = mutableListOf<BigIntegerRange>()

    var currLine = lines[0]
    var i = 0
    while (!(currLine.isEmpty() || currLine.isBlank())) {
        val (start, end) = currLine.split("-").map{ it.toBigInteger() }
        ranges.add(start..end)
        i++
        currLine = lines[i]
    }

    val ids = mutableListOf<BigInteger>()

    while (i+1 < lines.size) {
        i++
        currLine = lines[i]
        val id = currLine.toBigInteger()
        ids.add(id)
    }

    var count = 0

    for (id in ids) {
        for (range in ranges) {
            if (id in range) {
                count++
                break
            }
        }
    }

    return count.toString()
}

fun part2(input: String): String {
    val lines = input.lines()
    val ranges = mutableListOf<BigIntegerRange>()

    var currLine = lines[0]
    var i = 0
    while (!(currLine.isEmpty() || currLine.isBlank())) {
        val (start, end) = currLine.split("-").map{ it.toBigInteger() }
        ranges.add(start..end)
        i++
        currLine = lines[i]
    }

    val intersectedRanges = intersectRanges(ranges)

    var result = BigInteger("0")
    for (range in intersectedRanges) {
        result += (range.endInclusive-range.start+BigInteger.ONE)
    }

    return result.toString()
}

fun intersectRanges(ranges: List<BigIntegerRange>) : List<BigIntegerRange> {
    val sortedRanges = ranges.sortedBy { it.endInclusive }
    val intersectedRanges = mutableListOf<BigIntegerRange>()

    var interRange = sortedRanges.first()

    for (i in 1..<sortedRanges.size) {
        val currRange = sortedRanges[i]
        if (interRange.intersects(currRange)) {
            val newStart = if (interRange.start < currRange.start) interRange.start else currRange.start
            val newEnd = if (interRange.endInclusive > currRange.endInclusive) interRange.endInclusive else currRange.endInclusive
            interRange = BigIntegerRange(newStart, newEnd)
        }
        else {
            intersectedRanges.add(interRange)
            interRange = currRange
        }        
    }
    intersectedRanges.add(interRange)

    return intersectedRanges
}
