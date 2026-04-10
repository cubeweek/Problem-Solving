package problem.baekjoon.bronze.b2

import java.util.StringTokenizer

fun main(args: Array<String>) {
    val st = StringTokenizer(readln())
    val x = st.nextToken().toInt()
    val y = st.nextToken().toInt()

    println(if (x % 3 == 0 || y % 3 == 0) "YES" else "NO")
}