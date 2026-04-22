package contest.codeforce.ct2225

import kotlin.math.min

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val sb = StringBuilder()

    val testCnt = br.readLine().toInt()

    repeat(testCnt) {
        val n = br.readLine().toInt()
        val l1 = br.readLine()
        val l2 = br.readLine()
        val dp = IntArray(n + 1)

        for (i in 1 .. n) {
            val vert = dp[i-1] + if (l1[i-1] != l2[i-1]) 1 else 0
            val hori = if (i < 2) Int.MAX_VALUE else dp[i-2] + (if (l1[i-1] != l1[i-2]) 1 else 0) + (if (l2[i-1] != l2[i-2]) 1 else 0)
            dp[i] = min(vert, hori)
        }
        sb.append("${dp[n]}\n")
    }
    print(sb)
}