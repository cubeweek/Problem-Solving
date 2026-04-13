package problem.baekjoon.bronze.b2

import kotlin.math.round

fun main(args: Array<String>) {
    val scores = readln()

    val grades = mapOf<Char, Double>(
        'A' to 4.0,
        'B' to 3.0,
        'C' to 2.0,
        'D' to 1.0,
        'F' to 0.0,
        '+' to 0.5)

    var stdCnt = 0
    var totScore = 0.0
    for (i in 0 until scores.length) {
        totScore += grades[scores[i]]!!
        if ('+' != scores[i]) stdCnt++
    }

    println(totScore / stdCnt)
}