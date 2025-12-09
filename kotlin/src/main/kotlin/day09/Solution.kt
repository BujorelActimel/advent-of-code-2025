package day09

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun part1(input: String): String {
    val points = mutableListOf<Point>()

    for (line in input.lines()) {
        val (x, y) = line.split(",").map { it.toInt() }
        points.add(Point(x, y))
    }

    var maxArea: Long = 0
    for (i in 0..<points.size - 1) {
        val p1 = points[i]
        for (j in i + 1..<points.size) {
            val p2 = points[j]
            val area = (abs(p1.x - p2.x) + 1).toLong() * (abs(p1.y - p2.y) + 1).toLong()
            maxArea = max(maxArea, area)
        }
    }

    return maxArea.toString()
}

fun part2(input: String): String {
    val points = mutableListOf<Point>()

    for (line in input.lines()) {
        val (x, y) = line.split(",").map { it.toInt() }
        points.add(Point(x, y))
    }

    val rectangles = mutableListOf<Rectangle>()
    for (i in 0..<points.size - 1) {
        for (j in i + 1..<points.size) {
            rectangles.add(Rectangle.of(points[i], points[j]))
        }
    }
    rectangles.sortByDescending { it.area }

    val lines = mutableListOf<Rectangle>()
    for (i in 0..<points.size) {
        val p1 = points[i]
        val p2 = points[(i + 1) % points.size]
        lines.add(Rectangle.of(p1, p2))
    }

    for (rectangle in rectangles) {
        val inner = rectangle.inner()
        var overlaps = false

        for (line in lines) {
            if (line.overlaps(inner)) {
                overlaps = true
                break
            }
        }

        if (!overlaps) {
            return rectangle.area.toString()
        }
    }

    return "0"
}

data class Point(
    val x: Int,
    val y: Int,
)

data class Rectangle(
    val x: IntRange,
    val y: IntRange
) {
    val area: Long = x.size().toLong() * y.size().toLong()

    companion object {
        fun of(a: Point, b: Point): Rectangle {
            return Rectangle(
                min(a.x, b.x)..max(a.x, b.x),
                min(a.y, b.y)..max(a.y, b.y)
            )
        }
    }

    fun inner(): Rectangle {
        return Rectangle(
            x.first + 1..<x.last,
            y.first + 1..<y.last
        )
    }

    fun overlaps(other: Rectangle): Boolean {
        return x.overlaps(other.x) && y.overlaps(other.y)
    }
}

fun IntRange.overlaps(other: IntRange): Boolean {
    return max(first, other.first) <= min(last, other.last)
}

fun IntRange.size(): Int {
    return last - first + 1
}
