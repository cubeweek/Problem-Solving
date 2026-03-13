package problem.baekjoon.bronze.b5

import java.math.BigInteger

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val a = BigInteger(br.readLine())
    val b = BigInteger(br.readLine())
    println(a.add(b))
}