package problem.leetcode.easy

class MinimumCostOfBuyingCandiesWithDiscount {
    fun minimumCost(cost: IntArray): Int = cost.sortedDescending().filterIndexed { index, _ -> index % 3 != 2 } .sum()
}