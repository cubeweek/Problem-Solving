package problem.baekjoon.bronze.b2

import kotlin.math.min

fun main(args: Array<String>) {
    val counter = IntArray(26)
    val needs = mapOf('B' to 1,
        'R' to 2,
        'O' to 1,
        'N' to 1,
        'Z' to 1,
        'E' to 2,
        'S' to 1,
        'I' to 1,
        'L' to 1,
        'V' to 1)

    val n = readln().toInt()
    val str = readln()

    if (n < 12) println(0)
    else {
        for (s in str) ++counter[s.code - 65]

        var maxGoldChip = Int.MAX_VALUE
        for (n in needs.entries) maxGoldChip = min(counter[n.key.code - 65] / n.value, maxGoldChip)
        println(maxGoldChip)
    }
}