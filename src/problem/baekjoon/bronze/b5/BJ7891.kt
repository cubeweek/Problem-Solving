package problem.baekjoon.bronze.b5

import java.util.StringTokenizer

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val testCnt = br.readLine().toInt()

    val sb = StringBuilder()
    repeat(testCnt) {
        val st = StringTokenizer(br.readLine())
        sb.append(st.nextToken().toInt() + st.nextToken().toInt()).append("\n")
    }
    println(sb.toString())
}