package day02

import java.math.BigInteger
import kotlin.collections.emptyList
import kotlin.text.toBigInteger
import utils.getProperDivisors
import utils.concatTimes

fun part1(input: String): String {
    val ranges = input.split(",")
    var sum = BigInteger("0")
    val one = BigInteger("1")

    ranges.forEach {
        val (start, end) = it.split("-")
        var len = start.length
        var firstHalf = start.subSequence(0, len/2)
        if (len == 1) {
            firstHalf = start.subSequence(0, len)
        }
        var currentNumber = "$firstHalf$firstHalf".toBigInteger()
        val rangeStart = start.toBigInteger()
        val rangeEnd = end.toBigInteger()
        while (currentNumber <= rangeEnd) {
            if (currentNumber >= rangeStart) {
                sum += currentNumber
            }
            len = currentNumber.toString().length
            firstHalf = currentNumber.toString().subSequence(0, len/2).toString()
            var halfNumber = firstHalf.toBigInteger()
            halfNumber += one
            firstHalf = halfNumber.toString()
            currentNumber = "$firstHalf$firstHalf".toBigInteger()
        }
    }

    return sum.toString()
}

fun part2(input: String): String {
    val ranges = input.split(",")
    val rg = Regex("([0-9]+)\\1+")
    var sum = BigInteger("0")

    ranges.forEach {
        val (start, end) = it.split("-").map { it.toBigInteger() }

        var num = start
        while (num <= end) {
            if (rg.matches(num.toString())) {
                sum += num
            }
            num++
        }
    }
    return sum.toString()

    // the complicated solution that didnt work

    // val ranges = input.split(",")
    // val one = BigInteger("1")
    // var result = emptySet<BigInteger>().toMutableSet()

    // ranges.forEach {
    //     val (start, end) = it.split("-")

    //     val seqSizes = emptySet<Int>().toMutableSet()
    //     for (len in start.length..end.length) {
    //         val divs = getProperDivisors(len)
    //         for (div in divs) {
    //             seqSizes.add(div)
    //         }
    //     }

    //     println("$start-$end:\n  ${seqSizes.toList()}")


    //     for (size in seqSizes) {
    //         for (len in start.length..end.length) {
    //             if (len % size != 0) {
    //                 continue
    //             }
    
    //             var correctSeq = if (len == start.length) {
    //                 start
    //             } else if (len == end.length) {
    //                 end
    //             } else {
    //                 concatTimes("1".toString(), len)
    //             }

    //             val amount = len / size
    //             var seq = correctSeq.subSequence(0, size).toString()
    //             var currentNumber = concatTimes(seq, amount).toBigInteger()
    //             val rangeStart = start.toBigInteger()
    //             val rangeEnd = end.toBigInteger()
    //             var passes = 0

    //             println("  size $size\n  amount $amount")

    //             while (currentNumber <= rangeEnd) {
    //                 if (len == 1 && passes > 10) {
    //                     break
    //                 }
    //                 if (currentNumber >= rangeStart) {             
    //                     println("\t$currentNumber")           
    //                     result.add(currentNumber)
    //                 }
    //                 val seqNum = currentNumber.toString().subSequence(0, size).toString().toBigInteger() + one
    //                 seq = seqNum.toString()
    //                 currentNumber = concatTimes(seq, amount).toBigInteger()
    //                 passes++
    //             }
    //         }
    //     }
    // }

    // return result.toList().sum().toString();
}

// fun Iterable<BigInteger>.sum(): BigInteger {
//     var result = BigInteger("0")
//     for (num in this) {
//         result += num
//     }
//     return result
// }
