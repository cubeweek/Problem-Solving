package problem.leetcode.easy

import kotlin.math.*

class TwoFurthestHousesWithDifferentColors {
    fun maxDistance(colors: IntArray): Int {
        val list = Array (101) { intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE) }

        for (i in 0 until colors.size) {
            list[colors[i]][0] = min(list[colors[i]][0], i)
            list[colors[i]][1] = max(list[colors[i]][1], i)
        }

        var maxLen = -1
        for (i in 0..100) {
            if (list[i][0] == Int.MAX_VALUE) continue
            for (j in 0..100) {
                if (i == j || list[j][0] == Int.MAX_VALUE) continue
                maxLen = max(maxLen, abs(list[i][0] - list[j][1]))
                maxLen = max(maxLen, abs(list[i][1] - list[j][0]))
            }
        }

        return maxLen
    }
}