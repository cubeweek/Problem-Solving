package problem.leetcode.easy

/*
* 단계        | 질문        | 이 문제는..
* 상태 정의    | 뭘 저장?     | dp[i] = i를 2진수로 표현 시 1의 개수
* 점화식       | 이전→현재?   | dp[i] = dp[i >> 1] + (i & 1)
* 초기값       | 시작점?     | dp[0] = 0
* 순회 순서     | 어느 방향?  | 왼쪽 → 오른쪽 (bottom-up)
* */
class CountingBits {
    fun countBits(n: Int): IntArray {
        val dp = IntArray(n + 1) { 0 }

        for (i in 1..n) {
            dp[i] = dp[i shr 1] + (i and 1)
        }

        return dp
    }
}