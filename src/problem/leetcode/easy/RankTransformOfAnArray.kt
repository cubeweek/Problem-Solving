package problem.leetcode.easy

class RankTransformOfAnArray {
    fun arrayRankTransform(arr: IntArray): IntArray {
        val rankMap = arr.distinct().sorted().withIndex().associate { it.value to it.index + 1 }

        return IntArray(arr.size) { i -> rankMap[arr[i]]!! }
    }
}