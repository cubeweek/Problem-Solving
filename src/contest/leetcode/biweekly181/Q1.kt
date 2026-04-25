package contest.leetcode.biweekly181

class Q1 {
    fun validDigit(n: Int, x: Int): Boolean {
        var num = n
        var isValid = false
        while (num > 9) {
            if (num % 10 == x) isValid = true
            num /= 10
        }
        if (num == x) isValid = false
        return isValid
    }
}