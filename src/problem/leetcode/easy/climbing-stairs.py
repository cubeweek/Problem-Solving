package problem.leetcode.easy

class ClimbingStairs:
    def climbStairs_optimized(n: int) -> int:
        if n <= 2:
            return n

        one_step_before = 2  # DP[i-1] 역할
        two_steps_before = 1 # DP[i-2] 역할
        current = 0

        for i in range(3, n + 1):
            current = one_step_before + two_steps_before
            two_steps_before = one_step_before
            one_step_before = current

        return current