package utils

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
