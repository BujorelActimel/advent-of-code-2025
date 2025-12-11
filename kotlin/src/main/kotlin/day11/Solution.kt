package day11

fun part1(input: String): String {
    val graph = hashMapOf<String, List<String>>()

    for (line in input.lines()) {
        val (label, outputs) = line.split(": ")
        graph[label] = outputs.split(" ")
    }

    return countPaths(graph, "you", "out", listOf()).toString()
}

fun part2(input: String): String {
    val graph = hashMapOf<String, List<String>>()

    for (line in input.lines()) {
        val (label, outputs) = line.split(": ")
        graph[label] = outputs.split(" ")
    }

    val specialNodes = listOf("dac", "fft")

    return countPaths(graph, "svr", "out", specialNodes).toString()
}

fun countPaths(
    graph: Map<String, List<String>>,
    start: String,
    end: String,
    special: List<String>
): Long {
    val index = special.withIndex().associate { it.value to it.index }
    val ALL_MASK = if (special.isEmpty()) 0 else (1 shl special.size) - 1

    val memo = HashMap<Pair<String, Int>, Long>()

    fun dfs(node: String, mask: Int): Long {
        var newMask = mask
        val idx = index[node]
        if (idx != null) newMask = mask or (1 shl idx)

        val key = node to newMask
        memo[key]?.let { return it }

        if (node == end) {
            val res = if (newMask == ALL_MASK) 1L else 0L
            memo[key] = res
            return res
        }

        var sum = 0L
        for (next in graph[node] ?: emptyList()) {
            sum += dfs(next, newMask)
        }

        memo[key] = sum
        return sum
    }

    return dfs(start, 0)
}
