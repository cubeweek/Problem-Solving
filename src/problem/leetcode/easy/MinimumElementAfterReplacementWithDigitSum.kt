package problem.leetcode.easy

class MinimumElementAfterReplacementWithDigitSum {
    fun minElement(nums: IntArray): Int {
        var answer = Int.MAX_VALUE

        for (n in nums) {
            var now = n
            var sum = 0
            while (now != 0) {
                sum += now % 10
                now /= 10
            }
            if (sum < answer) answer = sum
        }

        return answer
    }
}