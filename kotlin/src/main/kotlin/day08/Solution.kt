package day08

import kotlin.math.sqrt
import java.util.PriorityQueue

fun part1(input: String): String {
    val points = mutableListOf<Point>()

    for ((i, line) in input.lines().withIndex()) {
        val (x, y, z) = line.split(",").map{ it -> it.toInt() }
        points.add(Point(i, x, y, z))
    }

    val queue = PriorityQueue<Distance>(Comparator { d1, d2 ->
        (d1.value-d2.value).toInt()
    })

    for (i in 0..<points.size-1) {
        for (j in i+1..<points.size) {
            val dist = points[i].distance(points[j])
            queue.add(Distance(i, j, dist))
        }
    }

    val groups = mutableListOf<Int>()
    var newGroupId = 1
    val groupedIds = HashMap<Int, Int>()

    var iters = 1000 // for real input

    while (queue.isNotEmpty() && iters > 0) {
        iters--
        val currDistance = queue.poll()
        val startGroup = groupedIds[currDistance.startId]
        val endGroup = groupedIds[currDistance.endId]

        if (startGroup != null && endGroup != null) {
            if (startGroup == endGroup) {
                continue
            }
            for (id in groupedIds.keys) {
                if (groupedIds[id] == endGroup) {
                    groupedIds[id] = startGroup
                }
            }
            groups.remove(endGroup)
        }
        else if (startGroup == null && endGroup == null) {
            groupedIds[currDistance.startId] = newGroupId
            groupedIds[currDistance.endId] = newGroupId
            groups.add(newGroupId)
            newGroupId++
        }
        else {
            if (startGroup == null) {
                groupedIds[currDistance.startId] = endGroup!!
            }
            else {
                groupedIds[currDistance.endId] = startGroup
            }
        }
    }

    val counts = mutableListOf<Int>()

    for (group in groups) {
        var countGroup = 0
        for (id in groupedIds.keys) {
            if (groupedIds[id] == group) {
                countGroup++
            }
        }
        counts.add(countGroup)
    }

    var ans = 1;
    val sortedCounts = counts.sorted().reversed().toMutableList()
    var index = 0
    for (count in sortedCounts) {
        if (index >= 3) {
            break
        }
        ans *= count
        index++
    }

    return ans.toString()
}

fun part2(input: String): String {
    val points = mutableListOf<Point>()

    for ((i, line) in input.lines().withIndex()) {
        val (x, y, z) = line.split(",").map{ it -> it.toInt() }
        points.add(Point(i, x, y, z))
    }

    val queue = PriorityQueue<Distance>(Comparator { d1, d2 ->
        (d1.value-d2.value).toInt()
    })

    for (i in 0..<points.size-1) {
        for (j in i+1..<points.size) {
            val dist = points[i].distance(points[j])
            queue.add(Distance(i, j, dist))
        }
    }

    val groups = mutableListOf<Int>()
    var newGroupId = 1
    val groupedIds = HashMap<Int, Int?>()
    for (id in points.indices) {
        groupedIds[id] = null
    }


    while (queue.isNotEmpty()) {
        val currDistance = queue.poll()
        val startGroup = groupedIds[currDistance.startId]
        val endGroup = groupedIds[currDistance.endId]

        if (startGroup != null && endGroup != null) {
            if (startGroup == endGroup) {
                continue
            }
            for (id in groupedIds.keys) {
                if (groupedIds[id] == endGroup) {
                    groupedIds[id] = startGroup
                }
            }
            groups.remove(endGroup)
        }
        else if (startGroup == null && endGroup == null) {
            groupedIds[currDistance.startId] = newGroupId
            groupedIds[currDistance.endId] = newGroupId
            groups.add(newGroupId)
            newGroupId++
        }
        else {
            if (startGroup == null) {
                groupedIds[currDistance.startId] = endGroup!!
            }
            else {
                groupedIds[currDistance.endId] = startGroup
            }
        }

        if (groupedIds.values.toSet().size == 1 && groupedIds[1] != null) {
            return (points[currDistance.startId].x.toLong() * points[currDistance.endId].x.toLong()).toString()
        }
    }

    return ""
}

data class Point(
    val id: Int,
    val x: Int,
    val y: Int,
    val z: Int,
)

data class Distance(
    val startId: Int,
    val endId: Int,
    val value: Double,
)

fun Point.distance(other: Point): Double {
    val xPart = (other.x - this.x).toLong() * (other.x - this.x).toLong()
    val yPart = (other.y - this.y).toLong() * (other.y - this.y).toLong()
    val zPart = (other.z - this.z).toLong() * (other.z - this.z).toLong()

    return sqrt((xPart + yPart + zPart).toDouble())
}
