package problem.baekjoon.bronze.b5

import kotlin.math.pow

fun main(args: Array<String>) {
    val n = readLine()!!.toDouble().pow(2.0).toLong()
    println(if (n <= 100_000_000) "Accepted" else "Time limit exceeded")
}