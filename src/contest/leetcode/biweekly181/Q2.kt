package contest.leetcode.biweekly181

    fun compareBitonicSums(nums: IntArray): Int {
        var asSum = nums[0] + 0L
        var i = 0
        while (++i < nums.size) {
            if (nums[i] > nums[i-1]) asSum += nums[i]
            else break
        }

        var deSum = nums[i-1] + 0L
        while (i < nums.size) deSum += nums[i++]

        return if (asSum == deSum) -1
                else if (asSum > deSum) 0
                else 1
    }

    fun main() {
        println(compareBitonicSums(intArrayOf(1,3,2,1)))
        println(compareBitonicSums(intArrayOf(2,4,5,2)))
        println(compareBitonicSums(intArrayOf(1,2,4,3)))
    }