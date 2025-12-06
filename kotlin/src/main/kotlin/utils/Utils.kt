package utils

import java.math.BigInteger

fun getProperDivisors(num: Int): List<Int> {
    val result = emptyList<Int>().toMutableList()
    for (div in num/2 downTo 1) {
        if (num % div == 0) {
            result.add(div)
        }
    }
    return result
}

fun concatTimes(str: String, times: Int): String {
    var result = ""
    for (i in 1..times) {
        result += str
    }
    return result
}

operator fun BigInteger.rangeTo(that: BigInteger) = BigIntegerRange(this, that)

class BigIntegerRange(
    override val start: BigInteger,
    override val endInclusive: BigInteger
) : ClosedRange<BigInteger>, Iterable<BigInteger> {

    override fun iterator(): Iterator<BigInteger> {
        return BigIntegerIterator(start, endInclusive)
    }

    fun intersects(other: BigIntegerRange): Boolean {
        return this.start in other || 
               this.endInclusive in other ||
               other.start in this ||
               other.endInclusive in this
    }
}

class BigIntegerIterator(val start: BigInteger, val endInclusive: BigInteger) : Iterator<BigInteger> {
    var initValue = start

    override fun hasNext(): Boolean {
        return initValue <= endInclusive
    }

    override fun next(): BigInteger {
        return initValue++
    }
}

fun String.columns(separator: Regex): List<String> {
    val columns = mutableListOf<String>()

    val splitLines = this.lines().map { it.trim().split(separator) }

    for (j in splitLines[0].indices) {
        var column = ""
        for (i in splitLines.indices) {
            column += (splitLines[i][j] + " ")
        }
        columns.add(column)
    }

    return columns.map{it.trim()}
}

fun String.matrix(): List<List<Char>> {
    val result = mutableListOf<List<Char>>()
    for (line in this.lines()) {
        val chars = mutableListOf<Char>()
        line.forEach { chars.add(it) }
        result.add(chars)
    }
    return result
}

fun String.pad(): String {
    val maxLen = this.lines().maxBy { it -> it.length }.length

    val padded = mutableListOf<String>()

    this.lines().forEach { line ->
        val newLine = line + concatTimes(" ", (maxLen-line.length))
        padded.add(newLine)
    }

    return padded.joinToString("\n")
}
