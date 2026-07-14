package problem.leetcode.easy

/*
* 단계        | 질문        |   이 문제는..
* 상태 정의    | 뭘 저장?     | dp[i] = i일차에 팔 때의 최대 이익
* 점화식       | 이전→현재?   | dp[i] = max(dp[i-1], prices[i] - minPrice)
* 초기값       | 시작점?     | dp[0] = 0, minPrice = prices[0]
* 순회 순서     | 어느 방향?  | 왼쪽 → 오른쪽 (bottom-up)
* */
class Solution {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        var minPrice = prices[0]
        var maxProfit = 0

        for (i in 1 until prices.size) {
            maxProfit = maxOf(maxProfit, prices[i] - minPrice)
            minPrice = minOf(minPrice, prices[i])
        }

        return maxProfit
    }
}