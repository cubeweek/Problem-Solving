package problem.leetcode.easy

class MinimumCommonValue {
    fun getCommon(nums1: IntArray, nums2: IntArray): Int {
        val len1 = nums1.size
        val len2 = nums2.size

        var c1 = 0
        var c2 = 0
        var answer = -1
        while (c1 < len1 && c2 < len2) {
            if (nums1[c1] == nums2[c2]) {
                answer = nums1[c1]
                break
            } else if (nums1[c1] < nums2[c2]) c1++
            else c2++
        }

        return answer
    }
}