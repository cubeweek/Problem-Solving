package problem.leetcode.easy

class CheckIfArrayIsSortedAndRotated {
    fun check(nums: IntArray): Boolean {
        var cnt = 0
        val n = nums.size
        for (i in 0 until n) {
            if (nums[i] > nums[(i + 1) % n]) cnt++
        }

        return cnt <= 1
    }
}