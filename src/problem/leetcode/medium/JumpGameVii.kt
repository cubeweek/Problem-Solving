package problem.leetcode.medium

import kotlin.math.max
import kotlin.math.min

class JumpGameVii {
    fun canReach(s: String, minJump: Int, maxJump: Int): Boolean {
        val n = s.length

        if (s[n - 1] == '1') return false

        val queue = ArrayDeque<Int>()
        queue.addLast(0)

        var farthest = 0

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()

            val start = max(curr + minJump, farthest + 1)
            val end = min(curr + maxJump, n - 1)

            for (j in start..end) {
                if (s[j] == '0') {
                    if (j == n - 1) return true
                    queue.addLast(j)
                }
            }

            farthest = max(farthest, curr + maxJump)
        }

        return false
    }
}