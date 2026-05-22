package problem.leetcode.medium

class FindThePrefixCommonArrayOfTwoArrays {
    fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
        val n = A.size
        val ans = IntArray(n)
        val count = IntArray(n + 1)
        var prefixCommon = 0

        for (i in 0 until n) {
            count[A[i]]++
            if (count[A[i]] == 2) {
                prefixCommon++
            }

            count[B[i]]++
            if (count[B[i]] == 2) {
                prefixCommon++
            }

            ans[i] = prefixCommon
        }

        return ans
    }
}