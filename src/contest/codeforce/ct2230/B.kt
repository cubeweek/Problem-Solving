package contest.codeforce.ct2230

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val sb = java.lang.StringBuilder()

    val tStr = br.readLine() ?: return
    val testCnt = tStr.toInt()

    val INF = 1e9.toInt()

    repeat(testCnt) {
        val s = br.readLine()!!

        var dp = IntArray(4) { INF }
        dp[0] = 0

        for (i in s.indices) {
            val c = s[i]
            val nextDp = IntArray(4) { dp[it] + 1 }

            when (c) {
                '1' -> {
                    // 이전 어떤 상태에서든 '1'을 붙이면 끝자리가 '1'인 상태가 됨
                    val prevMin = minOf(dp[0], dp[1], dp[2], dp[3])
                    if (prevMin != INF) nextDp[1] = min(nextDp[1], prevMin)
                }
                '2' -> {
                    // 끝이 '1'이나 '3'인 상태 뒤에 '2'가 오면 12, 32(4의 배수)가 되므로 불가.
                    // 끝이 없거나(0), '2'인 상태 뒤에만 '2'를 붙여 끝자리가 '2'인 상태를 만들 수 있음.
                    val prevMin = min(dp[0], dp[2])
                    if (prevMin != INF) nextDp[2] = min(nextDp[2], prevMin)
                }
                '3' -> {
                    // 이전 어떤 상태에서든 '3'을 붙이면 끝자리가 '3'인 상태가 됨
                    val prevMin = minOf(dp[0], dp[1], dp[2], dp[3])
                    if (prevMin != INF) nextDp[3] = min(nextDp[3], prevMin)
                }
            }
            dp = nextDp
        }

        // 최종 상태 중 가장 적게 지운 횟수가 정답
        val ans = minOf(dp[0], dp[1], dp[2], dp[3])
        sb.append(ans).append("\n")
    }
    print(sb)
}