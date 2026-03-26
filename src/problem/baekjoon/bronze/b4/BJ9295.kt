package problem.baekjoon.bronze.b4

import java.util.StringTokenizer

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val testCnt = br.readLine().toInt()
    val sb = StringBuilder()

    for (i in 1..testCnt) {
        val st = StringTokenizer(br.readLine())
        sb.append("Case $i: ").append(st.nextToken().toInt() + st.nextToken().toInt()).append("\n")
    }
    println(sb)
}