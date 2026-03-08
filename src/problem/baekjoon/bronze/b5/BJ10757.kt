package problem.baekjoon.bronze.b5

import java.math.BigInteger
import java.util.StringTokenizer

fun main(args: Array<String>) {
    val st = StringTokenizer(readLine())
    val a = BigInteger(st.nextToken())
    val b = BigInteger(st.nextToken())
    println(a.plus(b))
}