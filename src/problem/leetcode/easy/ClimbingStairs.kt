package problem.leetcode.easy

class ClimbingStairs {
    fun climbStairs(n: Int): Int {
        if (n <= 2) return n

        var oneStepBef = 2
        var twoStepBef = 1
        var curr = 0

        for (i in 3..n) {
            curr = oneStepBef + twoStepBef
            twoStepBef = oneStepBef
            oneStepBef = curr
        }

        return curr
    }
}